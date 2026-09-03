# 3. Arrays e `ArrayList`

## 3.1 Array estático

Um array possui tamanho definido na criação e posições contíguas identificadas por índice.

```java
int[] valores = new int[5];
valores[0] = 12;
valores[1] = 25;
```

Características:

- tamanho fixo;
- acesso direto por índice;
- todos os elementos possuem o mesmo tipo;
- índices de `0` até `length - 1`;
- posições de tipos primitivos recebem valores padrão, como `0` para `int`;
- posições de objetos começam com `null`.

## 3.2 Tamanho lógico e capacidade

Considere um array interno com 10 posições, mas apenas 3 utilizadas:

- **capacidade:** 10 posições disponíveis no array;
- **tamanho lógico:** 3 elementos realmente armazenados.

```java
int[] dados = new int[10];
int tamanho = 0;

dados[tamanho] = 22;
tamanho++;
```

`dados.length` informa a capacidade física do array. A variável `tamanho` controla a quantidade de elementos válidos.

## 3.3 Inserção manual

Inserir ao final, havendo espaço, é simples:

```java
dados[tamanho] = 39;
tamanho++;
```

Para inserir em um índice intermediário, os elementos posteriores precisam ser deslocados para a direita:

```java
static int inserir(int[] dados, int tamanho, int indice, int valor) {
    if (tamanho == dados.length) {
        throw new IllegalStateException("Array cheio");
    }
    if (indice < 0 || indice > tamanho) {
        throw new IndexOutOfBoundsException("Índice inválido");
    }

    for (int i = tamanho; i > indice; i--) {
        dados[i] = dados[i - 1];
    }

    dados[indice] = valor;
    return tamanho + 1;
}
```

## 3.4 Remoção manual

Para não deixar um buraco, os elementos posteriores são deslocados para a esquerda:

```java
static int remover(int[] dados, int tamanho, int indice) {
    if (indice < 0 || indice >= tamanho) {
        throw new IndexOutOfBoundsException("Índice inválido");
    }

    for (int i = indice; i < tamanho - 1; i++) {
        dados[i] = dados[i + 1];
    }

    dados[tamanho - 1] = 0;
    return tamanho - 1;
}
```

## 3.5 Como funciona um array dinâmico

Um array não cresce. Uma estrutura como `ArrayList` simula crescimento com este processo:

1. mantém internamente um array;
2. ao atingir a capacidade, cria um array maior;
3. copia os elementos antigos;
4. substitui a referência interna;
5. adiciona o novo elemento.

Uma versão didática:

```java
import java.util.Arrays;

class VetorDinamico {
    private int[] dados = new int[2];
    private int tamanho;

    public void adicionar(int valor) {
        garantirCapacidade();
        dados[tamanho] = valor;
        tamanho++;
    }

    private void garantirCapacidade() {
        if (tamanho == dados.length) {
            int novaCapacidade = dados.length + dados.length / 2;
            dados = Arrays.copyOf(dados, novaCapacidade);
        }
    }

    public int tamanho() {
        return tamanho;
    }
}
```

## 3.6 Criando um `ArrayList`

Use generics para indicar o tipo:

```java
import java.util.ArrayList;

ArrayList<Integer> numeros = new ArrayList<>();
ArrayList<String> nomes = new ArrayList<>(20);
```

No modelo apresentado nos slides, considera-se uma capacidade inicial de 10. Em versões modernas do OpenJDK, o construtor vazio normalmente adia a criação efetiva do array até a primeira inserção; nessa primeira expansão, a capacidade padrão costuma se tornar 10. Esse detalhe de implementação pode mudar entre versões e não altera o uso da classe.

Ao crescer, a implementação usual aumenta a capacidade em aproximadamente 50%. Portanto, uma capacidade 10 normalmente cresce para 15.

## 3.7 Principais métodos

### Adicionar ao final

```java
numeros.add(22);
numeros.add(39);
```

### Adicionar em um índice

```java
numeros.add(1, 99);
```

O elemento é inserido no índice 1 e os posteriores são deslocados. O índice pode variar de `0` até `size()`. Fora desse intervalo ocorre `IndexOutOfBoundsException`.

### Consultar

```java
int primeiro = numeros.get(0);
```

### Substituir

```java
numeros.set(0, 100);
```

`set` troca um elemento existente; não aumenta o tamanho da lista.

### Remover por índice

```java
Integer removido = numeros.remove(0);
```

Retorna o elemento removido e desloca os posteriores.

### Remover por objeto

```java
boolean encontrou = numeros.remove(Integer.valueOf(39));
```

Remove a primeira ocorrência igual ao objeto e retorna `true`; caso não encontre, retorna `false`.

### A armadilha com `Integer`

```java
ArrayList<Integer> lista = new ArrayList<>();
lista.add(10);
lista.add(20);
lista.add(30);

lista.remove(1);                  // remove o índice 1: valor 20
lista.remove(Integer.valueOf(30)); // remove o objeto 30
```

Como `1` é um `int`, Java escolhe `remove(int index)`. Use `Integer.valueOf` quando quiser remover o valor.

### Buscar e converter

```java
boolean contem = numeros.contains(99);
int indice = numeros.indexOf(99);
Object[] array = numeros.toArray();
Integer[] arrayTipado = numeros.toArray(new Integer[0]);
```

### Estado e limpeza

```java
int tamanho = numeros.size();
boolean vazia = numeros.isEmpty();
numeros.clear();
```

## 3.8 Percorrendo a lista

Por índice:

```java
for (int i = 0; i < numeros.size(); i++) {
    System.out.println(numeros.get(i));
}
```

Com `for-each`:

```java
for (Integer numero : numeros) {
    System.out.println(numero);
}
```

## 3.9 Exemplo completo

```java
import java.util.ArrayList;

public class ExemploArrayList {
    public static void main(String[] args) {
        ArrayList<String> herois = new ArrayList<>();

        herois.add("Mari");
        herois.add("Rafa");
        herois.add("Josi");
        herois.add(1, "Ana");

        herois.set(0, "Maria");
        herois.remove("Rafa");

        System.out.println(herois);
        System.out.println("Primeiro: " + herois.get(0));
        System.out.println("Quantidade: " + herois.size());
        System.out.println("Possui Josi? " + herois.contains("Josi"));
    }
}
```

Saída:

```text
[Maria, Ana, Josi]
Primeiro: Maria
Quantidade: 3
Possui Josi? true
```

## 3.10 `ArrayList` ou array?

Use array quando o tamanho é conhecido e fixo, ou quando a implementação do exercício exige controle manual. Use `ArrayList` quando a quantidade muda durante a execução e você quer operações prontas de lista.

