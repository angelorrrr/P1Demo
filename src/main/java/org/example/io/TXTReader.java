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
            try {
                Integer.parseInt(firstLine[0].trim());
                Graph<Integer, Double> graph = new AdjacencyListGraph<>(type, isWeighted);
                return isWeighted ?
                        populateGraphWithWeights(graph, br, firstLine, Integer::parseInt) :
                        populateGraphWithoutWeights(graph, br, firstLine, Integer::parseInt);
            } catch (NumberFormatException ignored) {
                Graph<String, Double> graph = new AdjacencyListGraph<>(type, isWeighted);
                return isWeighted ?
                        populateGraphWithWeights(graph, br, firstLine, s -> s) :
                        populateGraphWithoutWeights(graph, br, firstLine, s -> s);
            }
        }
    }

    private <T> Graph<T, Double> populateGraphWithWeights(Graph<T, Double> graph, BufferedReader br, String[] firstLine, Function<String, T> converter) throws IOException {
        T src = converter.apply(firstLine[0].trim());
        T dest = converter.apply(firstLine[1].trim());
        double weight = Double.parseDouble(firstLine[2].trim());
        graph.addVertex(src);
        graph.addVertex(dest);
        graph.addRelation(src, dest, weight);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 3) continue;
            src = converter.apply(parts[0].trim());
            dest = converter.apply(parts[1].trim());
            weight = Double.parseDouble(parts[2].trim());
            graph.addVertex(src);
            graph.addVertex(dest);
            graph.addRelation(src, dest, weight);
        }
        return graph;
    }

    private <T> Graph<T, Double> populateGraphWithoutWeights(Graph<T, Double> graph, BufferedReader br, String[] firstLine, Function<String, T> converter) throws IOException {
        T src = converter.apply(firstLine[0].trim());
        T dest = converter.apply(firstLine[1].trim());
        graph.addVertex(src);
        graph.addVertex(dest);
        graph.addRelation(src, dest, null);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 2) continue;
            src = converter.apply(parts[0].trim());
            dest = converter.apply(parts[1].trim());
            graph.addVertex(src);
            graph.addVertex(dest);
            graph.addRelation(src, dest, null);
        }
        return graph;
    }
}
