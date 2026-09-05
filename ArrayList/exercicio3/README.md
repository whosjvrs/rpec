# Exercício 3
## 3) Explique a diferença entre remove(int index) e remove(Object O) no ArrayList. Dê um exemplo em que o uso de números pode confundir o programador.
### R: A diferença básica entre os dois métodos está naa forma como o elemento é identificado e removido daa lista.
### - remove(int index): Remove o elemento que está em uma posição (indice) especificaa da lista. Ele recebe um número inteiro e retorna o objeto que foi removido.
### - remove(Object o): Remove a primeira ocorrência do objeto especificado na lista. Ele procura pelo elemento(usando o método equals()) e retorna um valor booleano (true se encontrou e removeu, ou false caso contráario)
### Exemplo onde o uso de números pode confundir
### A confusão acontece frequentemente quando criamos um ArrayList de números inteiros (ArrayList<Integer>). Como o Java possui recurso de autoboxing (conversão automática entre o tipo primitivo int e a classe Integer), passar um número puro para o método pode fazer o Java entender que você quer remover pelo índice, e não pelo valor.
### Veja o exemplo prático abaixo:
```java
import java.util.ArrayList;

public class ExemploArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        
        // Adicionando os números 10, 20 e 30 na lista
        numeros.add(10); // Índice 0
        numeros.add(20); // Índice 1
        numeros.add(30); // Índice 2

        // O programador quer remover o NÚMERO 20 da lista:
        numeros.remove(20); // Isso causará um ERRO (IndexOutOfBoundsException)!
    }
}
```
### Por que isso acontece?
### No código acima, ao passar numeros.remove(20), o Java entende que você está chamando remove(int index). Ele tentará remover o elemento no índice 20. Como a lista só vai até o índice 2, o programa quebra.
### Como resolver a confusão? 
### Se a intenção é remover o valor número 20, você precisa forçar o Java a entender o número como um objeto(Integer). Há duas formas comuns de fazer isso:
```java
// Forma 1: Fazendo o casting explícito para Object
numeros.remove((Object) 20);

// Forma 2: Criando explicitamente um objeto Integer
        numeros.remove(Integer.valueOf(20));
```
### Ambas as abordagens farão o Java chamar o método remove(Object O), removendo com sucesso o número 20 da lista.