# 4. Complexidade de algoritmos

Complexidade descreve como o custo de um algoritmo cresce conforme aumenta a entrada. Na disciplina, a notação mais comum é **Big O**.

## 4.1 Complexidades principais

| Notação | Nome | Ideia |
|---|---|---|
| `O(1)` | constante | custo não cresce com a quantidade de elementos |
| `O(log n)` | logarítmica | reduz o problema em partes a cada passo |
| `O(n)` | linear | pode visitar todos os elementos uma vez |
| `O(n log n)` | quase linear | comum em algoritmos eficientes de ordenação |
| `O(n²)` | quadrática | geralmente possui dois percursos aninhados |

## 4.2 Exemplos

### Acesso por índice: `O(1)`

```java
int valor = numeros[5];
```

O computador calcula diretamente o endereço da posição.

### Busca linear: `O(n)`

```java
static int buscar(int[] dados, int alvo) {
    for (int i = 0; i < dados.length; i++) {
        if (dados[i] == alvo) {
            return i;
        }
    }
    return -1;
}
```

No pior caso, todos os elementos são verificados.

### Dois laços aninhados: `O(n²)`

```java
for (int i = 0; i < dados.length; i++) {
    for (int j = 0; j < dados.length; j++) {
        System.out.println(dados[i] + ", " + dados[j]);
    }
}
```

## 4.3 Melhor, médio e pior caso

Em uma busca linear:

- melhor caso: o elemento está na primeira posição, `O(1)`;
- pior caso: está no fim ou não existe, `O(n)`;
- ao informar apenas Big O, geralmente se enfatiza um limite superior/pior crescimento relevante.

## 4.4 Complexidade amortizada

Adicionar ao final de um `ArrayList` geralmente custa `O(1)`. Eventualmente, a lista precisa criar um array maior e copiar `n` elementos, operação `O(n)`. Como essa cópia não ocorre em toda inserção, o custo médio distribuído por várias inserções é chamado de **amortizado**, permanecendo `O(1)` por adição ao final.

## 4.5 Custos do `ArrayList`

| Operação | Custo típico | Motivo |
|---|---:|---|
| `get(index)` | `O(1)` | acesso direto |
| `set(index, valor)` | `O(1)` | acesso direto |
| `add(valor)` no final | `O(1)` amortizado | normalmente escreve na próxima posição |
| `add(index, valor)` | `O(n)` | pode deslocar elementos |
| `remove(index)` | `O(n)` | pode deslocar elementos |
| `contains(valor)` | `O(n)` | busca sequencial |
| `indexOf(valor)` | `O(n)` | busca sequencial |

## 4.6 Custos de uma lista encadeada

| Operação | Custo típico |
|---|---:|
| acessar o índice `i` | `O(n)` |
| buscar um valor | `O(n)` |
| inserir/remover no início | `O(1)` |
| inserir no fim com referência para o último | `O(1)` |
| remover após um nó já conhecido | `O(1)` |

Uma lista encadeada não é automaticamente mais rápida. Ela melhora certas inserções e remoções, mas perde o acesso direto por índice e usa memória adicional para referências.

## 4.7 Custos de fila e pilha bem implementadas

| Estrutura | Operação principal | Custo esperado |
|---|---|---:|
| fila | inserir no fim | `O(1)` |
| fila | remover do início | `O(1)` |
| pilha | inserir no topo | `O(1)` |
| pilha | remover do topo | `O(1)` |
| deque | inserir/remover nas extremidades | `O(1)` |

Se uma fila com array desloca todos os elementos a cada remoção, `dequeue` passa a custar `O(n)`. A fila circular evita esse deslocamento ao mover índices.

## 4.8 Como analisar um exercício

Pergunte:

1. Qual é o tamanho da entrada (`n`)?
2. Existe um laço que percorre os elementos?
3. Há laços aninhados?
4. Existe deslocamento ou cópia de elementos?
5. A operação usa um índice direto ou precisa seguir referências?
6. Estou analisando o pior caso ou um custo amortizado?

