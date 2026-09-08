# Exercicio 7
## 7) Explique a diferença entre ArrayList e LinkedList.
### R: O ArrayList utiliza intermanete um array dinâmico, oferecendo acesso rápido por índice, mas inserções e remoções no início ou no meio podem exigir o deslocamento dos elementos. A LinkedList utiliza nós encadeados, não possui acesso direto por índice e precisa percorrer os nós, mas permite inserções e remoções nas extremidades. Portanto, a escolha depende das operações realizadas com maior frequência.
```java
ArrayList<String> nomes = new ArrayList<>();

nomes.add("Ana");
nomes.add("Bruno");
nomes.add("Carlos");

System.out.println(nomes.get(1)); // Bruno
```
```java
LinkedList<String> nomes = new LinkedList<>();

nomes.addLast("Ana");
nomes.addLast("Bruno");
nomes.addFirst("Carlos");

System.out.println(nomes); //[Carlos, Ana, Bruno]
``` 
### Use ArrayList quando: 
### - Precisar acessar elementos frequentemente por índice; Fizer muitas leituras; Adicionar principalmente no final; Quiser uma escolha geral para trabalhar com listas.
### Use LinkedList quando: 
### - Fizer muitas operações no início ou no fim; Precisar trabalhar com fila ou deque; Não precisar acessar frequentemennte por índice.