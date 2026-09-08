import java.util.ArrayList;

public class Exercicio6 {
    public static void main(String[] args){
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 0; i<12; i++){
            lista.add(i);
        }
        System.out.println(lista);
        System.out.println("Quantidade de elementos: " + lista.size());
    }
}