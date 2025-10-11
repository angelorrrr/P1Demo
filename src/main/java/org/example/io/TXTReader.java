package org.example.io;

import org.example.core.implementations.AdjacencyListGraph;
import org.example.core.interfaces.Graph;

import java.io.*;
import java.util.Objects;
import java.util.function.Function;

public class TXTReader {
    private final File file;

    public TXTReader(String filename) throws IOException {
        file = new File(filename);
    }

    /**
     * Lê um grafo a partir de um arquivo de texto, detectando automaticamente
     * o tipo do grafo, se é ponderado e se os vértices são do tipo Integer ou String.
     * @return Um objeto Grafo preenchido com os dados do arquivo.
     * @throws IOException Se o arquivo não for encontrado ou estiver vazio.
     */
    public Graph<?, Double> getGraph() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) {
                throw new IOException("Arquivo vazio.");
            }

            Graph.GraphType type = Graph.GraphType.fromValue(line.trim());
            line = br.readLine();
            if (line == null) {
                return new AdjacencyListGraph<String, Double>(type, false);
            }

            String[] firstLine = line.split(" ");
            if (firstLine.length != 3 && firstLine.length != 2)
                // Erro de digitação corrigido aqui (antes estava "Grado dora")
                throw new RuntimeException("Grafo fora dos padrões especificados");

            boolean isWeighted = (firstLine.length == 3);
            Graph<?, Double> graph = new AdjacencyListGraph<>(type, isWeighted);
            try {
                Integer.parseInt(firstLine[0]);
                populateGraph((Graph<Integer, Double>) graph, br, Integer::parseInt);
            }catch (NumberFormatException ignored) {
                populateGraph((Graph<String, Double>)graph, br, Function.identity()); // Function.identity() for String
            }

            return graph;
        }
    }
    private Double weightGetter(String[] line){
        if (line.length != 3)
            return Double.NaN;
        else
            return Double.parseDouble(line[2]);
    }
    private <T> void populateGraph(Graph<T, Double> graph,
                                   BufferedReader br,
                                   Function<String, T> converter) throws IOException {
        T src;
        T dest;
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            src = converter.apply(parts[0].trim());
            dest = converter.apply(parts[1].trim());
            Double weight = weightGetter(parts);
            graph.addVertex(src);
            graph.addVertex(dest);
            graph.addRelation(src, dest, weight);
        }
    }

}
