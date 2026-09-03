# 5. Nós e listas encadeadas

## 5.1 Conceito de encadeamento

Em um array, os elementos são acessados por posições. Em uma lista encadeada, cada elemento fica em um **nó** que guarda:

1. o dado;
2. uma referência para outro nó.

Os nós podem estar separados na heap. A sequência é definida pelas referências.

```text
inicio → [Mari | próximo] → [Rafa | próximo] → [Josi | null]
```

## 5.2 Nó genérico

```java
class No<E> {
    E elemento;
    No<E> proximo;

    No(E elemento) {
        this.elemento = elemento;
    }
}
```

`No<E>` permite armazenar qualquer tipo. O último nó aponta para `null`.

## 5.3 Lista simplesmente encadeada

```java
import java.util.NoSuchElementException;

class ListaEncadeada<E> {
    private No<E> inicio;
    private No<E> fim;
    private int tamanho;

    public void adicionarNoFim(E elemento) {
        No<E> novo = new No<>(elemento);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }

        tamanho++;
    }

    public void adicionarNoInicio(E elemento) {
        No<E> novo = new No<>(elemento);
        novo.proximo = inicio;
        inicio = novo;

        if (fim == null) {
            fim = novo;
        }

        tamanho++;
    }

    public E removerDoInicio() {
        if (inicio == null) {
            throw new NoSuchElementException("Lista vazia");
        }

        E removido = inicio.elemento;
        inicio = inicio.proximo;
        tamanho--;

        if (inicio == null) {
            fim = null;
        }

        return removido;
    }

    public boolean contem(E procurado) {
        No<E> atual = inicio;

        while (atual != null) {
            if (java.util.Objects.equals(atual.elemento, procurado)) {
                return true;
            }
            atual = atual.proximo;
        }

        return false;
    }

    public int tamanho() {
        return tamanho;
    }
}
```

## 5.4 Passo a passo da adição

Estado inicial:

```text
inicio = null
fim = null
```

Após adicionar `Mari`:

```text
inicio ─┐
        ▼
      [Mari | null]
        ▲
fim ────┘
```

Após adicionar `Rafa`:

```text
inicio → [Mari | próximo] → [Rafa | null] ← fim
```

O ponto decisivo é `fim.proximo = novo`; depois, `fim = novo`.

## 5.5 Passo a passo da remoção do início

Antes:

```text
inicio → [Mari] → [Rafa] → [Josi] ← fim
```

Operação:

```java
E removido = inicio.elemento;
inicio = inicio.proximo;
```

Depois:

```text
          inicio → [Rafa] → [Josi] ← fim
```

Se o último elemento for removido, tanto `inicio` quanto `fim` devem voltar a `null`.

## 5.6 Lista duplamente encadeada

Cada nó guarda referências para o anterior e o próximo:

```java
class NoDuplo<E> {
    E elemento;
    NoDuplo<E> anterior;
    NoDuplo<E> proximo;

    NoDuplo(E elemento) {
        this.elemento = elemento;
    }
}
```

Representação:

```text
null ← [Mari] ⇄ [Rafa] ⇄ [Josi] → null
```

Vantagem: navegação e remoção eficiente nos dois sentidos quando os nós/extremidades são conhecidos. Desvantagem: mais referências para atualizar e maior consumo de memória.

## 5.7 `ArrayList` versus `LinkedList`

| Critério | `ArrayList` | `LinkedList` |
|---|---|---|
| Base interna | array dinâmico | nós duplamente encadeados |
| Acesso por índice | rápido, `O(1)` | lento, `O(n)` |
| Inserção no fim | `O(1)` amortizado | `O(1)` |
| Inserção/remoção no início | `O(n)` | `O(1)` |
| Memória extra | capacidade não usada | referências anterior/próximo por nó |
| Localidade de memória | geralmente melhor | geralmente pior |

Na prática, `ArrayList` é uma boa escolha padrão para listas. `LinkedList` faz sentido quando as operações nas extremidades dominam ou quando ela é usada pelas interfaces `Queue`/`Deque`. Para filas e pilhas modernas em Java, `ArrayDeque` costuma ser preferível.

## 5.8 Métodos comuns de `LinkedList`

```java
import java.util.LinkedList;

LinkedList<String> nomes = new LinkedList<>();
nomes.addFirst("Mari");
nomes.addLast("Rafa");
String primeiro = nomes.getFirst();
String ultimo = nomes.getLast();
nomes.removeFirst();
nomes.removeLast();
```

## Erros comuns

- esquecer de ligar o nó antigo ao novo;
- mover `inicio` ou `fim` na ordem errada e perder parte da lista;
- não tratar a estrutura vazia;
- não fazer `fim = null` quando o último elemento é removido;
- comparar objetos com `==` em vez de `equals`/`Objects.equals`;
- imaginar que nós encadeados ficam fisicamente lado a lado.

