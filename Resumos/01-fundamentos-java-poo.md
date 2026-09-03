# 1. Fundamentos de Java e POO

Antes de implementar estruturas de dados, você precisa estar confortável com os elementos usados para construí-las.

## 1.1 Estrutura mínima de um programa

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Olá, estruturas de dados!");
    }
}
```

- `class`: define uma classe.
- `main`: ponto inicial do programa.
- `String[] args`: argumentos recebidos pela aplicação.
- `System.out.println`: imprime uma linha no console.

## 1.2 Variáveis, condições e repetição

```java
int quantidade = 3;
boolean vazia = quantidade == 0;

if (vazia) {
    System.out.println("Estrutura vazia");
} else {
    System.out.println("Existem " + quantidade + " elementos");
}

for (int i = 0; i < quantidade; i++) {
    System.out.println("Índice: " + i);
}
```

O `for` aparece constantemente para percorrer arrays. O `while` é útil quando o número de repetições depende do estado da estrutura:

```java
int i = 0;
while (i < quantidade) {
    System.out.println(i);
    i++;
}
```

## 1.3 Métodos

Um método agrupa uma operação e pode receber parâmetros e retornar um valor.

```java
static int somar(int a, int b) {
    return a + b;
}
```

Anatomia:

- `static`: pertence à classe, não a uma instância;
- `int`: tipo retornado;
- `somar`: nome do método;
- `(int a, int b)`: parâmetros;
- `return`: devolve o resultado.

Em estruturas de dados, métodos como `add`, `remove`, `push`, `pop`, `enqueue` e `dequeue` representam operações sobre os elementos armazenados.

## 1.4 Arrays básicos

```java
int[] numeros = new int[4];
numeros[0] = 10;
numeros[1] = 20;

System.out.println(numeros[0]);
System.out.println(numeros.length);
```

O índice começa em zero. Um array de tamanho 4 possui os índices `0`, `1`, `2` e `3`. Tentar acessar `numeros[4]` causa `ArrayIndexOutOfBoundsException`.

## 1.5 Classes e objetos

Uma classe define características e comportamentos. Um objeto é uma instância concreta dessa classe.

```java
public class Heroi {
    private String nome;
    private String habilidade;

    public Heroi(String nome, String habilidade) {
        this.nome = nome;
        this.habilidade = habilidade;
    }

    public String getNome() {
        return nome;
    }

    public void usarHabilidade() {
        System.out.println(nome + " usou " + habilidade);
    }
}
```

Uso:

```java
Heroi heroi = new Heroi("Peter", "teia");
heroi.usarHabilidade();
```

## 1.6 Os quatro pilares da POO

### Abstração

Modelar somente o que é relevante para o problema. Em uma fila de atendimento, talvez sejam necessários nome, senha e horário; altura e cor favorita provavelmente não importam.

### Encapsulamento

Proteger o estado interno da classe e permitir alterações por operações controladas.

```java
public class Conta {
    private double saldo;

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        saldo += valor;
    }

    public double getSaldo() {
        return saldo;
    }
}
```

Nas estruturas, o array interno, o topo da pilha e o início da fila normalmente são `private`. O usuário interage por métodos públicos.

### Herança

Permite que uma classe especializada herde características de outra.

```java
class Personagem {
    protected String nome;

    public Personagem(String nome) {
        this.nome = nome;
    }
}

class SuperHeroi extends Personagem {
    public SuperHeroi(String nome) {
        super(nome);
    }
}
```

Herança é parte da revisão de POO, mas não é necessária para implementar toda estrutura de dados.

### Polimorfismo

Permite chamar a mesma operação e obter comportamentos diferentes conforme o objeto.

```java
class Personagem {
    public void agir() {
        System.out.println("Personagem agindo");
    }
}

class SuperHeroi extends Personagem {
    @Override
    public void agir() {
        System.out.println("Super-herói usando habilidade");
    }
}
```

## 1.7 Referências e `null`

Variáveis de tipos primitivos guardam valores. Variáveis de objetos guardam referências para objetos.

```java
int a = 10;
int b = a;
b = 20; // a continua valendo 10

Heroi primeiro = new Heroi("Mari", "voo");
Heroi segundo = primeiro;
// primeiro e segundo referenciam o mesmo objeto
```

`null` significa que uma variável de referência não aponta para objeto algum.

```java
Heroi heroi = null;
// heroi.getNome(); // causaria NullPointerException
```

Esse conceito é essencial para entender os nós de listas encadeadas.

## 1.8 Generics

Generics permitem escrever uma estrutura que aceita diferentes tipos com segurança:

```java
ArrayList<String> nomes = new ArrayList<>();
ArrayList<Integer> numeros = new ArrayList<>();
```

O `E` visto em assinaturas como `add(E elemento)` representa o tipo do elemento:

```java
class Caixa<E> {
    private E elemento;

    public void guardar(E elemento) {
        this.elemento = elemento;
    }

    public E obter() {
        return elemento;
    }
}
```

## 1.9 Exceções e validação

Quando uma operação não pode ser realizada, a estrutura pode lançar uma exceção:

```java
if (tamanho == 0) {
    throw new IllegalStateException("Estrutura vazia");
}
```

Exceções comuns:

- `IndexOutOfBoundsException`: índice inexistente;
- `IllegalStateException`: estado atual impede a operação;
- `IllegalArgumentException`: argumento inválido;
- `NullPointerException`: tentativa de usar uma referência nula.

## 1.10 Records como dados compostos

Java não possui tuplas nativas. Um `record` é uma alternativa clara para agrupar informações:

```java
public record Atendimento(String nome, int prioridade, String horario) {}
```

Uso:

```java
Atendimento atendimento = new Atendimento("Ana", 2, "14:30");
System.out.println(atendimento.nome());
```

## Checklist antes de avançar

Você deve conseguir:

- criar e percorrer um array;
- escrever um método com parâmetro e retorno;
- criar uma classe com atributos privados e construtor;
- explicar a diferença entre valor e referência;
- reconhecer o significado de `null`;
- entender o papel de `<E>` ou `<T>` em uma classe genérica.

