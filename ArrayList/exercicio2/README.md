# Exercicio 2
## 2) Ao remover um elemento de um ArrayList por índice, o que acontece com os elementos seguintes na lista? Qual a complexidade dessa operação?

### Quando se remove um elemento de um ArrayList pelo índice, duas coisas acontecem com os elementos seguintes e com a estrutura da lista: 
### - Deslocamento: Todos os elementos à direita do item removido são movidos uma posição para a esquerda.
### - O índice de cada elemento restante subsequente diminui em exatamente 1.
### - O tamanho (size()) da lista é reduzido em 1 unidade.
### - A ultima posição original do array interna é definida em null para permitir a coleta de lixo (Garbage Collection)
### A complexidade de tempo depende de onde o elemento está localizado na lista:
### - Pior caso: O(n)(Linear) - Ocorre ao remover o primeiro elemento (índice 0), pois todos os outros n - 1 elemento precisam ser deslocados.
### - Caso médio: O(n)(Linear) - Em média, você precisará deslocar metade dos elementos da lista (n/2)
### - Melhor caso: O(1)(Constante) - Ocorre ao remover o último elemento da lista, pois nenhum outro elemento precisa ser movido.

```java
import java.util.ArrayList;
import java.util.Iterator;

public class ExemploArrayList {
    public static void main(String[] args) {
        // 1. Criação e Inicialização
        ArrayList<String> linguagens = new ArrayList<>();

        // 2. Adicionar elementos (Método add)
        linguagens.add("Java");       // Índice 0
        linguagens.add("Python");     // Índice 1
        linguagens.add("JavaScript"); // Índice 2
        linguagens.add("C++");        // Índice 3

        // 3. Verificar o tamanho (Método size)
        System.out.println("Tamanho inicial: " + linguagens.size()); // Saída: 4
        System.out.println("Lista original: " + linguagens);

        // 4. Acessar um elemento (Método get)
        String primeira = linguagens.get(0); 
        System.out.println("Elemento no índice 0: " + primeira); // Saída: Java

        // 5. Remover por ÍNDICE (Método remove)
        // Remove "Python" no índice 1. "JavaScript" e "C++" são deslocados para a esquerda.
        String removidaPorIndice = linguagens.remove(1); 
        System.out.println("\nRemovido por índice (1): " + removidaPorIndice);
        System.out.println("Nova lista após remoção: " + linguagens); // [Java, JavaScript, C++]
        System.out.println("Novo índice do JavaScript: " + linguagens.indexOf("JavaScript")); // Mudou de 2 para 1

        // 6. Remover por VALOR (Método remove que busca o objeto)
        boolean foiRemovido = linguagens.remove("C++");
        System.out.println("\nC++ foi removido por valor? " + foiRemovido);
        System.out.println("Lista atual: " + linguagens); // [Java, JavaScript]

        // 7. Modificar um elemento (Método set)
        linguagens.set(1, "TypeScript"); // Substitui "JavaScript" por "TypeScript"
        System.out.println("Após modificação: " + linguagens); // [Java, TypeScript]


        // =================================================================
        // ATENÇÃO: Como remover elementos dentro de um Loop Corretamente
        // =================================================================
        System.out.println("\n--- Teste de Remoção em Loop ---");
        
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(10);
        numeros.add(25);
        numeros.add(30);
        numeros.add(45);

        // MANEIRA ERRADA (Gera ConcurrentModificationException ou pula elementos):
        /*
        for (Integer num : numeros) {
            if (num > 20) {
                numeros.remove(num); // Erro em tempo de execução!
            }
        }
        */

        // MANEIRA CORRETA 1: Usando um Iterator explicitamente
        Iterator<Integer> iterator = numeros.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num % 2 == 0) { // Remove números pares (10 e 30)
                iterator.remove(); // Remove de forma segura sem quebrar o loop
            }
        }
        System.out.println("Após Iterator (remover pares): " + numeros); // Saída: [25, 45]

        // MANEIRA CORRETA 2 (Mais moderna - Java 8+): Método removeIf
        // Usa uma expressão lambda para remover quem atende ao critério
        numeros.removeIf(num -> num > 30); // Remove o 45
        System.out.println("Após removeIf (maiores que 30): " + numeros); // Saída: [25]
    }
}
``` 