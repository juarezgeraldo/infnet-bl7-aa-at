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

    private static void sort(int[] arr) {
        threeWayQuickSort(arr, 0, arr.length - 1);
    }

    private static void threeWayQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Encontrar pivôs
            int[] pivots = partition(arr, low, high);

            // Recursivamente classificar as três partições
            threeWayQuickSort(arr, low, pivots[0] - 1);
            threeWayQuickSort(arr, pivots[1] + 1, high);
        }
    }

    private static int[] partition(int[] arr, int low, int high) {
        int pivot1 = arr[low];
        int pivot2 = arr[high];

        if (pivot1 > pivot2) {
            swap(arr, low, high);
            pivot1 = arr[low];
            pivot2 = arr[high];
        } else if (pivot1 == pivot2) {
            // Se ambos os pivôs são iguais, encontrar um elemento diferente para o segundo pivô
            while (pivot1 == pivot2 && low < high) {
                low++;
                pivot1 = arr[low];
            }
        }

        int i = low + 1;
        int lt = low + 1;
        int gt = high - 1;

        while (i <= gt) {
            if (arr[i] < pivot1) {
                swap(arr, i, lt);
                lt++;
                i++;
            } else if (arr[i] > pivot2) {
                swap(arr, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        swap(arr, low, lt - 1);
        swap(arr, gt + 1, high);

        return new int[]{lt - 1, gt + 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
