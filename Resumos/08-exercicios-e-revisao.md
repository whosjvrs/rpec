# 8. Exercícios e revisão

Resolva na ordem. Nos exercícios de implementação, teste sempre:

- funcionamento normal;
- estrutura vazia;
- estrutura cheia, quando houver capacidade fixa;
- apenas um elemento;
- várias inserções e remoções alternadas.

## Nível 1 - Base de Java e POO

### 1. Classe e encapsulamento

Crie uma classe `Pedido` com `numero`, `descricao` e `atendido`. Proteja os atributos, crie construtor e um método `atender()`.

### 2. Polimorfismo

Crie uma superclasse `Personagem` com o método `usarHabilidade()` e duas subclasses com comportamentos diferentes.

### 3. Generics

Implemente uma classe `Caixa<T>` com os métodos `guardar`, `obter` e `estaVazia`.

Atividade complementar indicada no slide: [Liga dos 4 Pilares](https://lisianereipspucpr.github.io/liga-dos-4-pilares/).

## Nível 2 - Memória e referências

Responda sem consultar:

1. Cache é volátil ou não volátil?
2. Cite dois dispositivos não voláteis.
3. Qual é mais rápida: RAM ou SSD?
4. Qual é a função da Stack de chamadas?
5. Onde os objetos criados com `new` são armazenados conceitualmente?
6. Por que uma recursão sem parada pode causar `StackOverflowError`?
7. O que torna um objeto elegível para o Garbage Collector?
8. Stack de chamadas e pilha de dados são a mesma coisa?

<details>
<summary>Gabarito resumido</summary>

1. Volátil.
2. SSD, HD, pendrive ou fita magnética.
3. RAM.
4. Organizar frames de chamadas, parâmetros, variáveis locais e retornos.
5. Na heap.
6. Cada chamada cria um novo frame e nenhum deles é liberado.
7. Deixar de ser alcançável por referências ativas.
8. Não. Ambas seguem uma ideia LIFO, mas uma é parte da execução e a outra é uma estrutura criada pelo programa.

</details>

## Nível 3 - Arrays e `ArrayList`

### 1. Tamanho e capacidade

Explique a diferença entre o tamanho lógico e a capacidade de um `ArrayList`.

<details>
<summary>Resposta</summary>

Tamanho é a quantidade de elementos presentes e é obtido por `size()`. Capacidade é o tamanho do array interno disponível antes de uma nova expansão. A API pública comum não expõe diretamente essa capacidade.

</details>

### 2. Remoção por índice

O que acontece com os elementos posteriores quando um item é removido de um `ArrayList`? Qual é o custo?

<details>
<summary>Resposta</summary>

Os elementos posteriores são deslocados uma posição para a esquerda. No pior caso, a operação é `O(n)`.

</details>

### 3. Sobrecarga de `remove`

Explique a diferença entre `remove(int index)` e `remove(Object o)`. Dê um exemplo com `Integer`.

<details>
<summary>Resposta</summary>

O primeiro remove por índice e retorna o elemento; o segundo busca a primeira ocorrência igual ao objeto e retorna `boolean`. Em `ArrayList<Integer>`, `lista.remove(2)` remove o índice 2. Para remover o valor 2, use `lista.remove(Integer.valueOf(2))`.

</details>

### 4. Preveja a saída

```java
ArrayList<Integer> lista = new ArrayList<>();
lista.add(2);
lista.add(4);
lista.add(6);
lista.add(8);
lista.add(10);
lista.add(2, 99);
System.out.println(lista);
```

<details>
<summary>Resposta</summary>

```text
[2, 4, 99, 6, 8, 10]
```

O valor `99` entra no índice 2; os valores a partir de `6` são deslocados para a direita.

</details>

### 5. Remoção e deslocamento

```java
ArrayList<Integer> lista = new ArrayList<>();
lista.add(7);
lista.add(9);
lista.add(11);
lista.add(13);
lista.add(15);
lista.remove(1);
System.out.println(lista);
```

<details>
<summary>Resposta</summary>

```text
[7, 11, 13, 15]
```

O índice 1 continha o valor `9`.

</details>

### 6. Crescimento

Insira os números de 0 a 11 em um `ArrayList` criado sem capacidade explícita. Explique por que o tamanho e a capacidade não representam a mesma coisa.

<details>
<summary>Resposta</summary>

Após as 12 inserções, `size()` retorna 12. No comportamento usual do OpenJDK, o primeiro crescimento do construtor vazio cria capacidade 10 e, ao ultrapassá-la, ela cresce aproximadamente 50%, normalmente para 15. Capacidade é detalhe interno e pode variar por versão.

</details>

### 7. Pesquisa: `ArrayList` e `LinkedList`

Explique suas diferenças, duas vantagens/desvantagens de `LinkedList` e pelo menos dois métodos próprios para extremidades.

<details>
<summary>Resposta</summary>

`ArrayList` usa array dinâmico e oferece acesso por índice `O(1)`. `LinkedList` usa nós duplamente encadeados e o acesso por índice é `O(n)`. A lista encadeada permite inserções/remoções `O(1)` nas extremidades, mas consome referências extras e possui pior localidade de memória. Exemplos: `addFirst`, `addLast`, `removeFirst` e `removeLast`.

</details>

## Nível 4 - Encadeamento

### 1. Desenho de referências

Desenhe os nós após adicionar `Mari`, `Rafa` e `Josi`. Marque `inicio`, `fim`, `proximo` e `null`.

### 2. Lista mínima

Implemente uma lista genérica com:

- `adicionarNoInicio`;
- `adicionarNoFim`;
- `removerDoInicio`;
- `contem`;
- `tamanho`.

### 3. Caso de borda

Explique por que, ao remover o único elemento, não basta atualizar apenas `inicio`.

<details>
<summary>Resposta</summary>

Porque `fim` continuaria apontando para um nó que já não pertence logicamente à lista. Após a remoção do último elemento, as duas referências devem ser `null`.

</details>

## Nível 5 - Filas

### 1. Fila simples: resgates

Implemente uma fila simples de inteiros que permita inserir pedido, atender o próximo e mostrar os pedidos restantes.

### 2. Fila circular: Bat-Sinais

Implemente uma fila circular com capacidade 3. Faça esta sequência e anote `inicio`, `fim` e `tamanho` após cada passo:

1. inserir A;
2. inserir B;
3. inserir C;
4. remover;
5. inserir D;
6. remover até esvaziar.

### 3. Fila encadeada: Sala da Justiça

Implemente uma fila encadeada de nomes de heróis. Quem chega entra no fim e o Superman atende pelo início.

### 4. API do Java

Use `Queue<String>` e `ArrayDeque<String>` para simular cinco senhas. Utilize `offer`, `peek` e `poll`.

### 5. Questão conceitual

Por que remover repetidamente o índice zero de um `ArrayList` não é uma boa implementação de fila?

<details>
<summary>Resposta</summary>

Porque cada remoção desloca os elementos posteriores e custa `O(n)`. Uma fila circular, encadeada ou `ArrayDeque` remove do início em `O(1)`.

</details>

## Nível 6 - Pilhas e `Deque`

### 1. Pilha simples: escudo

Armazene ataques bloqueados em um array. `push` registra o ataque e `pop` devolve o último bloqueado.

### 2. Pilha encadeada: prédio

Cada andar alcançado pelo Homem-Aranha é empilhado. Ao descer, remova os andares até chegar ao térreo.

### 3. Pilha com `Deque`: portais

Use `Deque<String>` para abrir e fechar portais. O último portal aberto deve ser o primeiro fechado.

### 4. Deque: arena

Implemente entradas e saídas de heróis pela esquerda e pela direita com `addFirst`, `addLast`, `removeFirst` e `removeLast`.

### 5. Parênteses balanceados

Use uma pilha para verificar se uma expressão possui `()`, `[]` e `{}` corretamente balanceados.

Exemplos:

- `{[2 + (3 * 4)]}` → válido;
- `{[(])}` → inválido.

### 6. Desafio de desfazer/refazer

Use duas pilhas:

- `desfazer`: guarda ações executadas;
- `refazer`: guarda ações desfeitas.

Ao executar uma nova ação depois de desfazer, a pilha de refazer deve ser limpa.

## Modelo de teste manual

Para cada implementação própria, registre uma tabela:

| Operação | Estado antes | Resultado | Estado depois | Esperado? |
|---|---|---|---|---|
| `enqueue("A")` | fila vazia | sem retorno | `[A]` | sim |
| `dequeue()` | `[A]` | `A` | vazia | sim |
| `dequeue()` | vazia | exceção | vazia | sim |

## Checklist de domínio

Considere o conteúdo dominado quando você consegue:

- explicar FIFO e LIFO sem decorar exemplos;
- desenhar a movimentação de índices em uma fila circular;
- implementar `push`, `pop`, `enqueue` e `dequeue`;
- tratar estrutura vazia e cheia;
- explicar por que cada operação é `O(1)` ou `O(n)`;
- escolher array, `ArrayList`, lista encadeada, fila, pilha ou deque para um cenário;
- usar `Queue` e `Deque` da biblioteca Java;
- guardar objetos ou records, e não apenas números, nas estruturas.

