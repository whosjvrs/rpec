# 2. Memória, referências, Stack e Heap

Estruturas de dados existem na memória. Entender minimamente essa organização ajuda a visualizar arrays, objetos e nós encadeados.

## 2.1 Memória física e lógica

- **Memória física:** dispositivos reais que armazenam informações.
- **Memória lógica:** visão organizada fornecida pelo sistema operacional aos processos.

Dispositivos de memória variam principalmente em velocidade, capacidade, durabilidade e custo.

## 2.2 Memória volátil e não volátil

| Tipo | Características | Exemplos |
|---|---|---|
| Volátil | perde os dados ao desligar; rápida; menor capacidade; maior custo por unidade | cache e RAM |
| Não volátil | mantém os dados sem energia; maior capacidade; mais lenta | SSD, HD, pendrive e fita magnética |

### Hierarquia simplificada

1. **Cache:** muito rápida, pequena e próxima do processador.
2. **RAM:** armazena programas e dados durante a execução.
3. **SSD/HD:** armazenamento persistente com maior capacidade.
4. **Fitas:** armazenamento e backup de grande volume e acesso menos imediato.

## 2.3 Memória de um processo

Um processo recebe uma área lógica de memória organizada pelo sistema operacional. Para a disciplina, três regiões são especialmente importantes:

### Área de código e dados de leitura

Contém instruções do programa, literais e dados que não devem ser alterados. O modelo exato depende do sistema operacional, da JVM e do formato do executável, mas a ideia didática é separar conteúdo estável do estado criado durante a execução.

### Stack - pilha de chamadas

Mantém os frames das chamadas de métodos. Cada frame pode conter parâmetros, variáveis locais e informações necessárias para retornar ao método anterior.

```java
static int dobro(int numero) {
    int resultado = numero * 2;
    return resultado;
}
```

Ao chamar `dobro(5)`, um frame é criado. Quando o método termina, esse frame é removido automaticamente.

Chamadas demais, normalmente por recursão sem condição de parada, podem causar `StackOverflowError`:

```java
static void repetirParaSempre() {
    repetirParaSempre();
}
```

### Heap

É a região onde objetos e arrays são alocados dinamicamente:

```java
int[] numeros = new int[5];
Heroi heroi = new Heroi("Rafa", "velocidade");
```

As variáveis locais `numeros` e `heroi` fazem parte do contexto do método; os objetos criados com `new` ficam na heap. As variáveis contêm referências que permitem chegar até esses objetos.

## 2.4 Coletor de lixo

Em Java, a JVM administra a memória de objetos. Quando um objeto deixa de ser alcançável por referências ativas, ele se torna elegível para o **Garbage Collector**.

```java
Heroi heroi = new Heroi("Josi", "força");
heroi = null;
```

Após a atribuição de `null`, se nenhuma outra referência apontar para o objeto, ele poderá ser coletado. A coleta não necessariamente ocorre imediatamente.

## 2.5 Referências compartilhadas

```java
ArrayList<String> primeira = new ArrayList<>();
primeira.add("Mari");

ArrayList<String> segunda = primeira;
segunda.add("Rafa");

System.out.println(primeira); // [Mari, Rafa]
```

Não foram criadas duas listas. As duas variáveis apontam para o mesmo objeto na heap.

Para criar uma cópia superficial dos elementos:

```java
ArrayList<String> copia = new ArrayList<>(primeira);
```

## 2.6 Por que isso importa em listas encadeadas?

Cada nó é um objeto separado na heap. Os nós não precisam estar fisicamente lado a lado; a ordem lógica é definida por referências:

```java
class No {
    String elemento;
    No proximo;

    No(String elemento) {
        this.elemento = elemento;
    }
}
```

```java
No mari = new No("Mari");
No rafa = new No("Rafa");
mari.proximo = rafa;
```

O encadeamento é a ligação entre `mari` e `rafa`, não a posição física deles na memória.

## 2.7 Stack de memória não é a mesma coisa que `Stack` de dados

Os nomes são relacionados pela lógica LIFO, mas representam conceitos diferentes:

- **Stack de chamadas:** região usada automaticamente durante a execução dos métodos.
- **Pilha como estrutura de dados:** estrutura criada pelo programador para armazenar elementos segundo LIFO.

Uma pilha criada pelo programa normalmente é um objeto na heap, mesmo que seja referenciada por uma variável local.

## Quiz com respostas curtas

1. **A cache é volátil?** Sim.
2. **Dois dispositivos não voláteis:** SSD e HD.
3. **RAM ou SSD: qual é mais rápido?** RAM, pois foi projetada para acesso temporário durante o processamento e possui menor latência.
4. **Onde ficam variáveis locais?** No contexto/frame da chamada do método; objetos referenciados por elas ficam na heap.
5. **O que ocorre quando a RAM fica insuficiente?** O sistema pode usar memória virtual, sofrer forte perda de desempenho ou encerrar processos.
6. **Qual área exige gerenciamento dos objetos alocados?** Heap. Em Java isso é realizado principalmente pelo Garbage Collector.
7. **A heap é organizada como pilha?** Não. O nome Stack descreve a região com frames em ordem LIFO; a heap atende alocações dinâmicas.
8. **Uso de fita magnética:** backup e arquivamento de longo prazo.
9. **Por que a cache é pequena?** Memória extremamente rápida é cara e ocupa espaço próximo ao processador.
10. **Onde fica o código do programa?** Em uma área destinada ao código/instruções, dentro da organização lógica do processo/JVM.
11. **Livro mágico somente para leitura:** assim como literais e instruções, seu conteúdo pode ser consultado, mas não alterado livremente.
12. **Pratos demais na pilha:** representam chamadas demais sem retorno, podendo causar estouro da Stack.
13. **Terreno com caixas:** representa objetos espalhados na heap; o coletor identifica caixas não mais alcançáveis e libera seu espaço.

