# Exercicio 9
## 9) Cite dois métodos comuns da classe LinkedList e explique para que servem. 
### R: Dois métodos comnus da classe LinkedList são:
### - addFirst(elemento): adiciona  um elemento no início da lista.
```java
LinkedList<String> nomes = new LinkedList<>();
nomes.addFirst("Ana");
```
### - removeFirst(): remove e retorna o primeiro elemento da lista. Se ela estiver vazia, lança uma exceção.
```
String removido = nomes.removeFirst();
```