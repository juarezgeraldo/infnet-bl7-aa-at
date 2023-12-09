import service.*;

import java.io.IOException;
import java.util.Scanner;

//public class Application implements Comparable<Edge> {
public class Application {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Informe qual rotina quer executar:");
        System.out.println("1  - Algoritmo Genético");
        System.out.println("21 - Troco guloso");
        System.out.println("22 - Troco dinâmico");
        System.out.println("3  - Algoritmo Dijkstra");
        System.out.println("6  - Quicksort em 3 partes");

        int opcao = sc.nextInt();
        if (opcao == 1) new AlgoritmoGenetico();
        else if (opcao == 21) new TrocoRecursivoGuloso();
        else if (opcao == 22) new TrocoProgramacaoDinamica();
        else if (opcao == 3) new AlgoritmoDijkstra();
        else if (opcao == 6) new QuickSortTresPartes();
    }
}



