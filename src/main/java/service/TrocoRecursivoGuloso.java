package service;

import java.util.Arrays;

// A complexidade do algoritmo recursivo guloso é exponencial, O(2^n), devido ao grande número de chamadas recursivas.

public class TrocoRecursivoGuloso {

    private static final int[] MOEDAS = {1, 5, 10, 21, 25, 50};
    private static final int TROCO = 63;

    public TrocoRecursivoGuloso() {

        System.out.println("==== Algoritmo Recursivo Guloso ====");
        System.out.println("====================================");
        System.out.println("Dados iniciais: ");
        System.out.println("      Troco  : " + TROCO);
        System.out.println("      Moedas: ");
        Arrays.stream(MOEDAS).forEach(moeda -> System.out.print(String.format("%s ", moeda)));
        System.out.println("\n-----------------------------------");
        System.out.println("Qtd. moedas  : " + calcularTrocoRecursivoGuloso(MOEDAS, TROCO));
    }

    private static int calcularTrocoRecursivoGuloso(int[] MOEDAS, int TROCO) {
        Arrays.sort(MOEDAS);
        return calcularTrocoRecursivoGulosoAux(MOEDAS, TROCO, MOEDAS.length - 1);
    }

    private static int calcularTrocoRecursivoGulosoAux(int[] MOEDAS, int TROCO, int indiceMoeda) {
        if (TROCO == 0) {
            return 0;
        }

        if (indiceMoeda < 0 || TROCO < MOEDAS[0]) {
            return Integer.MAX_VALUE; // Valor impossível de alcançar
        }

        int incluirMoeda = Integer.MAX_VALUE;
        if (TROCO >= MOEDAS[indiceMoeda]) {
            incluirMoeda = 1 + calcularTrocoRecursivoGulosoAux(MOEDAS, TROCO - MOEDAS[indiceMoeda], indiceMoeda);
        }

        int excluirMoeda = calcularTrocoRecursivoGulosoAux(MOEDAS, TROCO, indiceMoeda - 1);

        return Math.min(incluirMoeda, excluirMoeda);
    }
}
