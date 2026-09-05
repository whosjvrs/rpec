# Exercicio 1
## 1) Explique a diferença entre tamanho e capacidade em um ArrayList.
### R: A principal diferença é que o tamanho(size) inidica o número de elementos atualmente armazenados no ArrayList, enquanto a capacidade (capacity) representa o endereço total alocado na memória para guardar esses elementos antes de precisar de um novo redimensionamento.

```java
import java.util.ArrayList;

public class ExemploArrayList {
public static void main(String[] args) {
// Inicializa com capacidade para 50 itens (espaço ocupado: 0, espaço livre: 50)
ArrayList<String> lista = new ArrayList<>(50);

        lista.add("Java");
        lista.add("Kotlin");

        System.out.println("Tamanho (Ocupado): " + lista.size()); // Saída: 2
        System.out.println("Posições ocupadas: Índice 0 e Índice 1");

        // Otimiza a memória: remove as 48 posições livres restantes
        lista.trimToSize(); 
        // Agora a capacidade interna é exatamente 2 (espaço livre: 0)
    }
}

```