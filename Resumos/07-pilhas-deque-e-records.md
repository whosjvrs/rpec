# 7. Pilhas, `Deque` e dados compostos

## 7.1 Pilha

Uma pilha segue **LIFO** (*Last In, First Out*): o último elemento inserido é o primeiro removido.

Exemplos: desfazer ações, histórico de navegação, avaliação de expressões e controle de chamadas de métodos.

Operações principais:

- `push(E elemento)`: empilha;
- `pop()`: remove e retorna o topo;
- `peek()`: consulta o topo;
- `isEmpty()`: verifica se está vazia;
- `size()`: informa a quantidade.

## 7.2 Pilha simples com array

```java
import java.util.NoSuchElementException;

class PilhaSimples {
    private final int[] dados;
    private int topo = -1;

    public PilhaSimples(int capacidade) {
        dados = new int[capacidade];
    }

    public void push(int valor) {
        if (topo == dados.length - 1) {
            throw new IllegalStateException("Pilha cheia");
        }
        dados[++topo] = valor;
    }

    public int pop() {
        if (topo == -1) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return dados[topo--];
    }

    public int peek() {
        if (topo == -1) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return dados[topo];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public int size() {
        return topo + 1;
    }
}
```

Observe a diferença:

- `dados[++topo] = valor`: primeiro aumenta `topo`, depois usa o índice;
- `dados[topo--]`: primeiro lê o valor, depois diminui `topo`.

## 7.3 Pilha encadeada

Cada novo nó aponta para o topo anterior:

```java
import java.util.NoSuchElementException;

class PilhaEncadeada<E> {
    private static class No<E> {
        E valor;
        No<E> anterior;

        No(E valor, No<E> anterior) {
            this.valor = valor;
            this.anterior = anterior;
        }
    }

    private No<E> topo;
    private int tamanho;

    public void push(E valor) {
        topo = new No<>(valor, topo);
        tamanho++;
    }

    public E pop() {
        if (topo == null) {
            throw new NoSuchElementException("Pilha vazia");
        }

        E valor = topo.valor;
        topo = topo.anterior;
        tamanho--;
        return valor;
    }

    public E peek() {
        if (topo == null) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return topo.valor;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public int size() {
        return tamanho;
    }
}
```

Inserção e remoção são `O(1)` porque a referência `topo` aponta diretamente para o nó relevante.

## 7.4 Pilha com `Deque`

Embora Java possua a classe antiga `Stack`, a documentação moderna recomenda usar `Deque`, geralmente com `ArrayDeque`:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Historico {
    public static void main(String[] args) {
        Deque<String> pilha = new ArrayDeque<>();

        pilha.push("Página inicial");
        pilha.push("Produtos");
        pilha.push("Produto 42");

        System.out.println(pilha.peek()); // Produto 42
        System.out.println(pilha.pop());  // remove Produto 42
        System.out.println(pilha.peek()); // Produtos
    }
}
```

## 7.5 `Deque`: fila com duas extremidades

`Deque` significa *Double Ended Queue*. Permite inserir, remover e consultar nas duas pontas.

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(1);
deque.addLast(2);
deque.addLast(3);

System.out.println(deque.removeFirst()); // 1
System.out.println(deque.removeLast());  // 3
```

### Métodos principais

| Operação | Primeira extremidade | Última extremidade |
|---|---|---|
| inserir e retornar estado | `offerFirst(e)` | `offerLast(e)` |
| inserir ou lançar exceção | `addFirst(e)` | `addLast(e)` |
| remover ou retornar `null` | `pollFirst()` | `pollLast()` |
| remover ou lançar exceção | `removeFirst()` | `removeLast()` |
| consultar ou retornar `null` | `peekFirst()` | `peekLast()` |
| consultar ou lançar exceção | `getFirst()` | `getLast()` |

Para usar como fila, insira no fim e remova do início. Para usar como pilha, insira e remova da mesma extremidade.

## 7.6 Dados compostos: tuplas e records

Tupla não é uma fila nem uma pilha. Ela representa um conjunto de valores que pode ser armazenado dentro de qualquer estrutura.

Java não possui uma tupla nativa de uso geral. Opções incluem classes, `record`, `Map.Entry` e bibliotecas que fornecem `Pair`. Para domínio de aplicação, `record` costuma comunicar melhor o significado dos dados.

### Fila de atendimento hospitalar

```java
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Queue;

record Paciente(String nome, int prioridade, LocalTime chegada) {}

Queue<Paciente> fila = new ArrayDeque<>();
fila.offer(new Paciente("Ana", 1, LocalTime.of(9, 30)));
```

Observação: uma fila FIFO simples não ordena automaticamente pela prioridade. Se prioridade definir quem será atendido primeiro, a estrutura adequada pode ser `PriorityQueue`.

### Pilha de navegação

```java
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

record PaginaVisitada(String url, LocalDateTime acesso, int scroll) {}

Deque<PaginaVisitada> historico = new ArrayDeque<>();
historico.push(new PaginaVisitada(
        "https://exemplo.com",
        LocalDateTime.now(),
        320
));
```

## 7.7 Pilha e recursão

Cada chamada recursiva cria um novo frame na Stack de chamadas:

```java
static int fatorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * fatorial(n - 1);
}
```

Para `fatorial(4)`, as chamadas aguardam na ordem `4 → 3 → 2 → 1` e retornam na ordem inversa, seguindo LIFO.

## 7.8 Escolhendo entre fila, pilha e deque

| Necessidade | Estrutura |
|---|---|
| atender por ordem de chegada | fila |
| recuperar o último item inserido | pilha |
| operar nas duas extremidades | deque |
| atender pelo maior peso/prioridade | fila de prioridade |

O problema determina a política de acesso; depois você escolhe a implementação considerando capacidade, memória e complexidade.

## Erros comuns

- confundir o topo com o último índice livre;
- executar `pop` antes de verificar se a pilha está vazia;
- chamar `peek` acreditando que o elemento será removido;
- usar uma fila FIFO quando o requisito pede prioridade;
- confundir pilha de dados com Stack de chamadas;
- escolher `Stack` por causa do nome quando `ArrayDeque` atende melhor.

