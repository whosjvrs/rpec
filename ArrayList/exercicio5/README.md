# Exercício 5
## 5) Dada a lista: 
```java
ArrayList lista = new ArrayList<>();  
lista.add(7);  
lista.add(9);  
lista.add(11);  
lista.add(13);  
lista.add(15);  
lista.remove(1);  
System.out.println(lista);  
```
## Qual será a saída? Explique por quê.
### R: A saída será (7,11,13,15). O valor que tinha sido adicionado no índice 1 era o 9, ao utilizar o método remove o 9 é apagado e o 11 asssume a posição.
