package service;

import java.util.Arrays;
import java.util.Random;

public class AlgoritmoGenetico {

    private static final int TAMANHO_POPULACAO_INICIAL = 20;
    private static final int TAMANHO_POPULACAO_CRUZAMENTO = 30;
    private static final double PROBABILIDADE_MUTACAO = 0.005; // 0.5%

    public AlgoritmoGenetico() {
        int[][] populacao = gerarPopulacaoInicial();

        for (int geracao = 0; geracao < 1000; geracao++) {
            calcularAptidao(populacao);
            ordenarPopulacao(populacao);

            if (populacao[0][6] == 6) {
                System.out.println("====    Algoritmo Genético    ====");
                System.out.println("==================================");
                System.out.println("Dados iniciais: ");
                System.out.println("      População inicial     : " + TAMANHO_POPULACAO_INICIAL);
                System.out.println("      População cruzamento  : " + TAMANHO_POPULACAO_CRUZAMENTO);
                System.out.println("      Probabilidade mutação : " + (PROBABILIDADE_MUTACAO * 100) + "%");
                System.out.println("-----------------------------------");
                System.out.println("Objetivo atingido na geração: " + geracao);
                break;
            }

            int[][] novaPopulacao = new int[TAMANHO_POPULACAO_CRUZAMENTO][7];

            for (int i = 0; i < TAMANHO_POPULACAO_CRUZAMENTO; i += 2) {
                int[] pai1 = selecao(populacao);
                int[] pai2 = selecao(populacao);

                int pontoCrossover = new Random().nextInt(5) + 1;

                int[] filho1 = crossover(pai1, pai2, pontoCrossover);
                int[] filho2 = crossover(pai2, pai1, pontoCrossover);

                mutacao(filho1);
                mutacao(filho2);

                novaPopulacao[i] = filho1;
                novaPopulacao[i + 1] = filho2;
            }

            populacao = novaPopulacao;
        }
    }

    private static int[][] gerarPopulacaoInicial() {
        int[][] populacao = new int[TAMANHO_POPULACAO_INICIAL][7];
        Random random = new Random();

        for (int i = 0; i < TAMANHO_POPULACAO_INICIAL; i++) {
            for (int j = 0; j < 6; j++) {
                populacao[i][j] = random.nextInt(2);
            }
            populacao[i][6] = 0;
        }


        return populacao;
    }

    private static void calcularAptidao(int[][] populacao) {
        for (int i = 0; i < populacao.length; i++) {
            int aptidao = Arrays.stream(populacao[i]).sum();
            populacao[i][6] = aptidao; // Armazenando a aptidão no último elemento do vetor
        }
    }

    private static void ordenarPopulacao(int[][] populacao) {
        Arrays.sort(populacao, (a, b) -> Integer.compare(b[6], a[6]));
    }

    private static int[] selecao(int[][] populacao) {
        return Arrays.copyOf(populacao[new Random().nextInt(TAMANHO_POPULACAO_INICIAL)], 7);
    }

    private static int[] crossover(int[] pai1, int[] pai2, int pontoCrossover) {
        int[] filho = new int[7];
        System.arraycopy(pai1, 0, filho, 0, pontoCrossover);
        System.arraycopy(pai2, pontoCrossover, filho, pontoCrossover, 6 - pontoCrossover);
        return filho;
    }

    private static void mutacao(int[] individuo) {
        if (Math.random() < PROBABILIDADE_MUTACAO) {
            int quantidadeGenesMutados = new Random().nextInt(4); // Até 3 genes podem sofrer mutação
            for (int i = 0; i < quantidadeGenesMutados; i++) {
                int gene = new Random().nextInt(5);
                individuo[gene] = 1 - individuo[gene]; // Troca 0 por 1 e vice-versa
            }
        }
    }
}
