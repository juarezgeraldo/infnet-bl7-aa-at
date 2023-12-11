package service;

import java.util.*;

public class AlgoritmoDijkstra {

    private static final int QTD_VERTICES = 6;

    public AlgoritmoDijkstra() {

        Grafo graph = new Grafo(QTD_VERTICES);

        graph.adicionaBorda(0, 1, 2);
        graph.adicionaBorda(0, 2, 4);
        graph.adicionaBorda(1, 2, 1);
        graph.adicionaBorda(1, 3, 7);
        graph.adicionaBorda(2, 4, 3);
        graph.adicionaBorda(3, 4, 1);
        graph.adicionaBorda(3, 5, 5);
        graph.adicionaBorda(4, 5, 2);

        int origemVertice = 0;

        int[] distancia = graph.dijkstra(origemVertice);

        System.out.println("==== Algoritmo Dijkstra ====");
        System.out.println("============================");
        System.out.println("Dados iniciais: ");
        System.out.println("      Qtd. Vértices  : " + QTD_VERTICES);
        System.out.println("--------------------------");

        for (int i = 0; i < QTD_VERTICES; i++) {
            System.out.println("A menor distancia entre " + origemVertice + " e " + i + " é: " + distancia[i]);
        }
    }
     static class Grafo {
        private int qtdVertices;
        private List<Borda>[] adjacente;

        public Grafo(int qtdVertices) {
            this.qtdVertices = qtdVertices;
            this.adjacente = new ArrayList[qtdVertices];
            for (int i = 0; i < qtdVertices; ++i) {
                this.adjacente[i] = new ArrayList<>();
            }
        }

        public void adicionaBorda(int ori, int dest, int peso) {
            this.adjacente[ori].add(new Borda(dest, peso));
            this.adjacente[dest].add(new Borda(ori, peso)); // Remove this line for directed graph
        }

        public int[] dijkstra(int origemVertice) {
            int[] distancia = new int[qtdVertices];
            Arrays.fill(distancia, Integer.MAX_VALUE);
            distancia[origemVertice] = 0;

            PriorityQueue<No> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distancia));
            pq.add(new No(origemVertice, 0));

            while (!pq.isEmpty()) {
                int u = pq.poll().vertice;

                for (Borda borda : adjacente[u]) {
                    int v = borda.destino;
                    int peso = borda.peso;

                    if (distancia[u] != Integer.MAX_VALUE && distancia[u] + peso < distancia[v]) {
                        distancia[v] = distancia[u] + peso;
                        pq.add(new No(v, distancia[v]));
                    }
                }
            }

            return distancia;
        }
    }

    static class Borda {
        private int destino;
        private int peso;

        public Borda(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    static class No {
        private int vertice;
        private int distancia;

        public No(int vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }

}
