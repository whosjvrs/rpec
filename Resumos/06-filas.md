# 6. Filas

Uma fila segue **FIFO** (*First In, First Out*): o primeiro elemento inserido é o primeiro removido.

Exemplos: atendimento por senha, documentos de impressão, pedidos e tarefas aguardando processamento.

## 6.1 Operações conceituais

- `enqueue(E elemento)`: insere no final;
- `dequeue()`: remove e retorna o primeiro;
- `peek()`: consulta o primeiro sem remover;
- `isEmpty()`: verifica se está vazia;
- `size()`: informa a quantidade.

Uma fila bem implementada mantém as operações de inserção e remoção em `O(1)`.

## 6.2 Fila simples com array

Esta implementação corresponde ao modelo didático do slide:

```java
class FilaSimples {
    private final int[] dados;
    private int inicio;
    private int fim;
    private int tamanho;

    public FilaSimples(int capacidade) {
        dados = new int[capacidade];
    }

    public void enqueue(int valor) {
        if (fim == dados.length) {
            throw new IllegalStateException("Fila cheia");
        }

        dados[fim] = valor;
        fim++;
        tamanho++;
    }

    public int dequeue() {
        if (tamanho == 0) {
            throw new IllegalStateException("Fila vazia");
        }

        int valor = dados[inicio];
        inicio++;
        tamanho--;
        return valor;
    }
}
```

Problema: as posições removidas no começo não são reutilizadas. Se `fim` chegar ao final do array, a fila é considerada cheia mesmo que existam posições livres antes de `inicio`.

## 6.3 Fila circular

A fila circular reaproveita o início do array. O operador `%` faz o índice voltar para zero após atingir o final.

```java
import java.util.NoSuchElementException;

class FilaCircular<E> {
    private final Object[] dados;
    private int inicio;
    private int fim;
    private int tamanho;

    public FilaCircular(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva");
        }
        dados = new Object[capacidade];
    }

    public void enqueue(E elemento) {
        if (tamanho == dados.length) {
            throw new IllegalStateException("Fila cheia");
        }

        dados[fim] = elemento;
        fim = (fim + 1) % dados.length;
        tamanho++;
    }

    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (tamanho == 0) {
            throw new NoSuchElementException("Fila vazia");
        }

        E removido = (E) dados[inicio];
        dados[inicio] = null;
        inicio = (inicio + 1) % dados.length;
        tamanho--;
        return removido;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (tamanho == 0) {
            throw new NoSuchElementException("Fila vazia");
        }
        return (E) dados[inicio];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }
}
```

### Simulação com capacidade 3

1. insere A: `inicio=0`, `fim=1`, `tamanho=1`;
2. insere B: `inicio=0`, `fim=2`, `tamanho=2`;
3. remove A: `inicio=1`, `fim=2`, `tamanho=1`;
4. insere C: `fim` volta para 0;
5. insere D na posição reaproveitada.

## 6.4 Fila encadeada

Não possui capacidade fixa. Mantemos referências para o primeiro e o último nó.

```java
import java.util.NoSuchElementException;

class FilaEncadeada<E> {
    private static class No<E> {
        E valor;
        No<E> proximo;

        No(E valor) {
            this.valor = valor;
        }
    }

    private No<E> inicio;
    private No<E> fim;
    private int tamanho;

    public void enqueue(E valor) {
        No<E> novo = new No<>(valor);

        if (fim != null) {
            fim.proximo = novo;
        } else {
            inicio = novo;
        }

        fim = novo;
        tamanho++;
    }

    public E dequeue() {
        if (inicio == null) {
            throw new NoSuchElementException("Fila vazia");
        }

        E valor = inicio.valor;
        inicio = inicio.proximo;
        tamanho--;

        if (inicio == null) {
            fim = null;
        }

        return valor;
    }

    public E peek() {
        if (inicio == null) {
            throw new NoSuchElementException("Fila vazia");
        }
        return inicio.valor;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int size() {
        return tamanho;
    }
}
```

### Invariantes importantes

- fila vazia: `inicio == null` e `fim == null`;
- primeiro elemento: `inicio == fim`;
- `fim.proximo` sempre deve ser `null`;
- após remover o último, `fim` também precisa virar `null`.

## 6.5 Usando a API `Queue` do Java

Em código de aplicação, prefira a interface `Queue` com `ArrayDeque`:

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class Atendimento {
    public static void main(String[] args) {
        Queue<String> fila = new ArrayDeque<>();

        fila.offer("Senha 01");
        fila.offer("Senha 02");
        fila.offer("Senha 03");

        System.out.println(fila.peek()); // consulta Senha 01
        System.out.println(fila.poll()); // remove Senha 01
        System.out.println(fila);        // [Senha 02, Senha 03]
    }
}
```

### Pares de métodos da interface `Queue`

| Objetivo | Retorna valor especial | Lança exceção |
|---|---|---|
| inserir | `offer(e)` | `add(e)` |
| remover primeiro | `poll()` retorna `null` se vazia | `remove()` |
| consultar primeiro | `peek()` retorna `null` se vazia | `element()` |

Na maioria dos casos, `offer`, `poll` e `peek` tornam o tratamento de estrutura vazia/limitada mais explícito.

## 6.6 Quando usar cada implementação

| Implementação | Vantagem | Limitação |
|---|---|---|
| fila simples com array | fácil de aprender | desperdiça posições removidas |
| fila circular | usa toda a capacidade do array | tamanho máximo fixo |
| fila encadeada | cresce dinamicamente | usa memória extra por nó |
| `ArrayDeque` | rápida e pronta para produção | não aceita `null` |

## 6.7 Exemplo com dado composto

```java
import java.util.ArrayDeque;
import java.util.Queue;

record PedidoResgate(int numero, String local) {}

public class CentralResgate {
    public static void main(String[] args) {
        Queue<PedidoResgate> pedidos = new ArrayDeque<>();

        pedidos.offer(new PedidoResgate(101, "Centro"));
        pedidos.offer(new PedidoResgate(102, "Bairro Norte"));

        PedidoResgate proximo = pedidos.poll();
        System.out.println("Atendendo: " + proximo);
    }
}
```

## Erros comuns

- remover do fim e transformar a fila em pilha;
- usar `ArrayList.remove(0)` repetidamente, causando deslocamento `O(n)`;
- não distinguir fila cheia de fila vazia em uma implementação circular;
- esquecer de atualizar `fim` ao remover o último nó;
- alterar `inicio` antes de guardar o valor que será retornado.

