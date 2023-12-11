package service;

import java.util.Arrays;

public class QuickSortTresPartes {

    private static final int[] LISTA_ENTRADA = {4, 2, 5, 1, 5, 6, 3};

    public QuickSortTresPartes() {

        System.out.println("==== QuickSort em três partes ====");
        System.out.println("==================================");
        System.out.println("Dados iniciais: ");
        System.out.println("      Lista de entrada: ");
        Arrays.stream(LISTA_ENTRADA).forEach(elemento -> System.out.print(String.format("%s ", elemento)));
        System.out.println("\n-----------------------------------");
        System.out.println("      Lista classificada: ");

        sort(LISTA_ENTRADA);

        Arrays.stream(LISTA_ENTRADA).forEach(elemento -> System.out.print(String.format("%s ", elemento)));
    }

    private static void sort(int[] lista) {
        quickSort(lista, 0, lista.length - 1);
    }

    private static void quickSort(int[] lista, int indMinimo, int indMaximo) {
        if (indMinimo < indMaximo) {
            int[] pivos = particao(lista, indMinimo, indMaximo);

            quickSort(lista, indMinimo, pivos[0] - 1);
            quickSort(lista, pivos[1] + 1, indMaximo);
        }
    }

    private static int[] particao(int[] lista, int indMinimo, int indMaximo) {
        int pivo1 = lista[indMinimo];
        int pivo2 = lista[indMaximo];

        if (pivo1 > pivo2) {
            troca(lista, indMinimo, indMaximo);
            pivo1 = lista[indMinimo];
            pivo2 = lista[indMaximo];
        } else if (pivo1 == pivo2) {
            while (pivo1 == pivo2 && indMinimo < indMaximo) {
                indMinimo++;
                pivo1 = lista[indMinimo];
            }
        }

        int i = indMinimo + 1;
        int menor = indMinimo + 1;
        int maior = indMaximo - 1;

        while (i <= maior) {
            if (lista[i] < pivo1) {
                troca(lista, i, menor);
                menor++;
                i++;
            } else if (lista[i] > pivo2) {
                troca(lista, i, maior);
                maior--;
            } else {
                i++;
            }
        }

        troca(lista, indMinimo, menor - 1);
        troca(lista, maior + 1, indMaximo);

        return new int[]{menor - 1, maior + 1};
    }

    private static void troca(int[] lista, int i, int j) {
        int temp = lista[i];
        lista[i] = lista[j];
        lista[j] = temp;
    }

}
