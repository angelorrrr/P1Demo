package org.example;

import org.example.algorythms.DFS;
import org.example.graph.interfaces.Graph;
import org.example.util.GraphView;
import org.example.util.TXTReader;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        
        System.setProperty("org.graphstream.ui", "swing");
        System.setProperty("java.awt.headless", "true");

        if (args.length == 0) {
            System.out.println("Erro: Faltou o nome do arquivo de entrada!");
            return;
        }
        String nomeArquivo = args[0];

        System.out.println("Lendo arquivo: " + nomeArquivo);
        TXTReader meuLeitor = new TXTReader(nomeArquivo);
        Graph<String, Double> meuGrafo = meuLeitor.getGraph();
        
        if (meuGrafo == null) {
            System.out.println("Nao consegui carregar o grafo.");
            return;
        }
        
        System.out.println("Iniciando analise de comunidades...");
        DFS<String, Double> meuAnalisador = new DFS<>();
        List<List<String>> resultados = meuAnalisador.findConnectedComponents(meuGrafo);

        System.out.println("\n>> Analise Concluida:");
        System.out.println("Total de comunidades encontradas: " + resultados.size());
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("Grupo " + (i + 1) + ": " + resultados.get(i));
        }
        
        System.out.println("\nGerando imagem do grafo...");
        GraphView visualizador = new GraphView(meuGrafo);
        visualizador.generatePNG("visualizacao_grafo.png");
        System.out.println("Imagem 'visualizacao_grafo.png' foi salva.");

        System.out.println("\nExecucao terminada.");
    }
}