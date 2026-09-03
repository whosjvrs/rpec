# 9. Referência rápida

## Conceitos

| Termo | Significado |
|---|---|
| array | coleção de tamanho fixo acessada por índice |
| tamanho lógico | quantidade de elementos válidos |
| capacidade | quantidade de posições disponíveis internamente |
| nó | objeto que guarda dado e referência(s) |
| encadeamento | ligação lógica entre nós por referências |
| FIFO | primeiro a entrar, primeiro a sair |
| LIFO | último a entrar, primeiro a sair |
| Stack | pilha de chamadas ou, conforme o contexto, estrutura LIFO |
| Heap | região de alocação dinâmica de objetos |
| Garbage Collector | mecanismo da JVM que recupera memória de objetos inalcançáveis |
| Big O | descrição do crescimento do custo de um algoritmo |

## `ArrayList<E>`

| Método | Efeito | Custo típico |
|---|---|---:|
| `add(e)` | adiciona no fim | `O(1)` amortizado |
| `add(i, e)` | insere no índice | `O(n)` |
| `get(i)` | consulta índice | `O(1)` |
| `set(i, e)` | substitui índice | `O(1)` |
| `remove(i)` | remove por índice | `O(n)` |
| `remove(obj)` | busca e remove objeto | `O(n)` |
| `contains(obj)` | verifica ocorrência | `O(n)` |
| `indexOf(obj)` | retorna primeiro índice ou `-1` | `O(n)` |
| `size()` | retorna tamanho | `O(1)` |
| `isEmpty()` | verifica se vazia | `O(1)` |
| `clear()` | remove todos | `O(n)` |
| `toArray()` | cria array com os elementos | `O(n)` |

## `LinkedList<E>` nas extremidades

| Método | Efeito |
|---|---|
| `addFirst(e)` | adiciona no início |
| `addLast(e)` | adiciona no fim |
| `getFirst()` | consulta primeiro; lança exceção se vazia |
| `getLast()` | consulta último; lança exceção se vazia |
| `removeFirst()` | remove primeiro; lança exceção se vazia |
| `removeLast()` | remove último; lança exceção se vazia |
| `peekFirst()` | consulta primeiro; retorna `null` se vazia |
| `peekLast()` | consulta último; retorna `null` se vazia |
| `pollFirst()` | remove primeiro; retorna `null` se vazia |
| `pollLast()` | remove último; retorna `null` se vazia |

## `Queue<E>`

| Objetivo | Sem exceção | Com exceção |
|---|---|---|
| inserir | `offer(e)` | `add(e)` |
| remover início | `poll()` | `remove()` |
| consultar início | `peek()` | `element()` |

Implementação comum:

```java
Queue<String> fila = new ArrayDeque<>();
```

## `Deque<E>` como pilha

| Método | Efeito |
|---|---|
| `push(e)` | adiciona no topo |
| `pop()` | remove o topo |
| `peek()` | consulta o topo |

Implementação comum:

```java
Deque<String> pilha = new ArrayDeque<>();
```

## `Deque<E>` nas duas extremidades

| Operação | Início | Fim |
|---|---|---|
| inserir | `offerFirst(e)` | `offerLast(e)` |
| remover | `pollFirst()` | `pollLast()` |
| consultar | `peekFirst()` | `peekLast()` |

## Comparação de estruturas

| Estrutura | Acesso por índice | Inserir/remover no início | Inserir/remover no fim | Tamanho |
|---|---:|---:|---:|---|
| array | `O(1)` | `O(n)` | `O(1)` se houver espaço | fixo |
| `ArrayList` | `O(1)` | `O(n)` | `O(1)` amortizado | dinâmico |
| lista encadeada | `O(n)` | `O(1)` | `O(1)` com referência ao fim | dinâmico |
| fila circular | não é objetivo principal | remover `O(1)` | inserir `O(1)` | capacidade fixa |
| fila encadeada | não é objetivo principal | remover `O(1)` | inserir `O(1)` | dinâmico |
| pilha | apenas topo | não aplicável | `push`/`pop` `O(1)` | depende da implementação |
| deque | não é objetivo principal | `O(1)` | `O(1)` | dinâmico em `ArrayDeque` |

## Decisão rápida

- Precisa acessar frequentemente `lista.get(i)`? Use `ArrayList`.
- Tamanho é fixo e conhecido? Considere array.
- Precisa atender pela chegada? Use `Queue`.
- Precisa desfazer ou recuperar o último? Use `Deque` como pilha.
- Precisa operar nas duas pontas? Use `Deque`.
- O exercício exige entender ponteiros/referências? Implemente nós encadeados.
- Precisa ordenar por prioridade? Estude `PriorityQueue`; FIFO simples não resolve.

## Esqueletos mentais

Fila encadeada:

```text
inserir: ligue fim → novo; mova fim
remover: guarde inicio.valor; mova inicio → inicio.proximo
```

Pilha encadeada:

```text
push: novo.anterior = topo; topo = novo
pop: guarde topo.valor; topo = topo.anterior
```

Fila circular:

```text
próximo índice = (índice atual + 1) % capacidade
```

