# Trilha de Estudos - Estruturas de Dados em Java

Esta trilha organiza o conteúdo da disciplina **Resolução de Problemas Estruturados em Computação** em uma sequência lógica: primeiro a base de Java e POO, depois memória e referências, arrays, listas dinâmicas, encadeamento, filas, pilhas e `Deque`.

O material foi construído a partir dos slides disponibilizados pela professora e complementado com explicações, exemplos em Java, análise de complexidade e exercícios de fixação.

## Ordem recomendada

1. [Fundamentos de Java e POO](01-fundamentos-java-poo.md)
2. [Memória, referências, Stack e Heap](02-memoria-e-referencias.md)
3. [Arrays e ArrayList](03-arrays-e-arraylist.md)
4. [Complexidade de algoritmos](04-complexidade.md)
5. [Nós e listas encadeadas](05-listas-encadeadas.md)
6. [Filas](06-filas.md)
7. [Pilhas, Deque e dados compostos](07-pilhas-deque-e-records.md)
8. [Exercícios e revisão](08-exercicios-e-revisao.md)
9. [Referência rápida](09-referencia-rapida.md)

## Plano de estudo sugerido

Cada sessão pode durar entre **45 e 75 minutos**:

| Sessão | Assunto | Resultado esperado |
|---|---|---|
| 1 | Sintaxe Java, métodos e arrays | Ler e escrever pequenos programas |
| 2 | Classes, objetos e encapsulamento | Modelar uma entidade simples |
| 3 | Herança, polimorfismo, generics e records | Entender os recursos usados nas estruturas |
| 4 | Memória física, RAM, Stack e Heap | Explicar onde dados e objetos são armazenados |
| 5 | Arrays e movimentação de elementos | Implementar inserção e remoção manual |
| 6 | `ArrayList` | Usar seus principais métodos sem confundir sobrecargas |
| 7 | Complexidade Big O | Comparar custos de acesso, busca, inserção e remoção |
| 8 | Nós e encadeamento | Implementar uma pequena lista encadeada |
| 9 | Fila simples e circular | Entender FIFO e reaproveitamento de posições |
| 10 | Fila encadeada e API `Queue` | Implementar e utilizar filas em Java |
| 11 | Pilha com array e encadeada | Entender LIFO e implementar `push`/`pop` |
| 12 | `Deque`, records e revisão | Escolher a estrutura adequada para cada problema |

## Como estudar cada tópico

Para cada arquivo:

1. Leia a explicação sem copiar o código.
2. Desenhe a estrutura no papel e simule duas ou três operações.
3. Digite o exemplo no IntelliJ.
4. Execute com casos normais, estrutura vazia e estrutura cheia.
5. Resolva pelo menos um exercício sem consultar a resposta.
6. Explique em voz alta por que a solução funciona e qual é sua complexidade.

## Mapa do conteúdo dos slides

| Material | Conteúdo nesta trilha |
|---|---|
| Aula 2 - POO | abstração, encapsulamento, herança e polimorfismo |
| Aula 3 - Informação no computador | memória volátil e não volátil, cache, RAM, áreas de um processo, Stack e Heap |
| Aula 4 - Array dinâmico | array estático, tamanho, capacidade, crescimento do `ArrayList`, inserção, remoção e métodos |
| Aula 5 - Pilha e fila encadeadas | nós, referências, FIFO, LIFO, fila simples/circular/encadeada, pilha simples/encadeada, `Deque` e tuplas |

## Regra central da disciplina

Não escolha uma estrutura apenas porque conhece sua implementação. Primeiro identifique **como os dados precisam ser acessados**:

- acesso direto por índice: array ou `ArrayList`;
- atendimento por ordem de chegada: fila;
- recuperação do último elemento inserido: pilha;
- inserção e remoção nas duas pontas: `Deque`;
- tamanho muito variável e muitos encadeamentos: considere uma estrutura ligada.

