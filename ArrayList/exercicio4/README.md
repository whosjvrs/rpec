# Exercício 4
## 4) Considere o seguinte código:
```java
ArrayList lista = new ArrayList<>();
lista.add(2);  
lista.add(4);  
lista.add(6);  
lista.add(8);  
lista.add(10);  
lista.add(2, 99);  
System.out.println(lista);  
```
## Qual será a saída no console? Explique o que aconteceu.
### R: A saida será: (2,4,99,6,8,10) Os valores foram adicionar em ordem pelo método .add, e quando o valor 99 foi adicionado no índice 2, os valores antigos foram empurrados para a direita.