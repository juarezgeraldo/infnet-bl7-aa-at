package service;// A complexidade do algoritmo de programação dinâmica é O(n * m), onde n é o valor do TROCO e m é o número de diferentes tipos de MEDAS. Essa complexidade é derivada do preenchimento da tabela.

import java.util.Arrays;

public class TrocoProgramacaoDinamica {

    private static final int[] MOEDAS = {1, 5, 10, 21, 25, 50};
    private static final int TROCO = 63;

    public TrocoProgramacaoDinamica() {

        System.out.println("==== Algoritmo Programação Dinâmica ====");
        System.out.println("========================================");
        System.out.println("Dados iniciais: ");
        System.out.println("      Troco  : " + TROCO);
        System.out.println("      Moedas: ");
        Arrays.stream(MOEDAS).forEach(moeda -> System.out.print(String.format("%s ", moeda)));
        System.out.println("\n---------------------------------------");
        System.out.println("Qtd. MEDAS  : " + calcularTrocoProgramacaoDinamica(MOEDAS, TROCO));
    }

    private static int calcularTrocoProgramacaoDinamica(int[] MEDAS, int TROCO) {
        int[] tabela = new int[TROCO + 1];
        Arrays.fill(tabela, Integer.MAX_VALUE);
        tabela[0] = 0;

        for (int i = 1; i <= TROCO; i++) {
            for (int j = 0; j < MEDAS.length; j++) {
                if (MEDAS[j] <= i) {
                    int subproblema = tabela[i - MEDAS[j]];
                    if (subproblema != Integer.MAX_VALUE && subproblema + 1 < tabela[i]) {
                        tabela[i] = subproblema + 1;
                    }
                }
            }
        }

        return tabela[TROCO];
    }
}
