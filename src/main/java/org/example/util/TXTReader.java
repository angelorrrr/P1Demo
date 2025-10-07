package org.example.util;

import org.example.entity.GraphType;
import org.example.graph.implementations.AdjacencyListGraph;
import org.example.graph.interfaces.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class TXTReader {
    private final File file;

    public TXTReader(String filename) {
        filename = System.getProperty("user.dir") + File.separator + "src\\main\\java\\org\\example" + File.separator + filename;
        this.file = new File(filename);
    }
    public Graph<?, Double> getWeightedGraph() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) {
                throw new IOException("Arquivo vazio.");
            }

            GraphType type = GraphType.fromValue(line.trim());
            line = br.readLine();
            if (line == null) {
                return new AdjacencyListGraph<String, Double>(type, false);
            }

            String[] firstLine = line.split(" ");
            if (firstLine.length != 3) {
                throw new IOException("Esperado 3 elementos por linha para grafo com peso.");
            }

            try {
                Integer.parseInt(firstLine[0].trim()); // vértices são inteiros
                Graph<Integer, Double> graph = new AdjacencyListGraph<>(type, true);
                return populateGraphWithWeights(graph, br, firstLine, Integer::parseInt);
            } catch (NumberFormatException ignored) {
                Graph<String, Double> graph = new AdjacencyListGraph<>(type, true);
                return populateGraphWithWeights(graph, br, firstLine, s -> s);
            }
        }
    }
    public Graph<?, Void> getGraph() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) {
                throw new IOException("Arquivo vazio.");
            }

            GraphType type = GraphType.fromValue(line.trim());
            line = br.readLine();
            if (line == null) {
                return new AdjacencyListGraph<String, Void>(type, false);
            }

            String[] firstLine = line.split(" ");
            if (firstLine.length != 2) {
                throw new IOException("Esperado 2 elementos por linha para grafo sem peso.");
            }

            try {
                Integer.parseInt(firstLine[0].trim());
                Graph<Integer, Void> graph = new AdjacencyListGraph<>(type, false);
                return populateGraphWithoutWeights(graph, br, firstLine, Integer::parseInt);
            } catch (NumberFormatException ignored) {
                Graph<String, Void> graph = new AdjacencyListGraph<>(type, false);
                return populateGraphWithoutWeights(graph, br, firstLine, s -> s);
            }
        }
    }
    private <T> Graph<T, Double> populateGraphWithWeights(Graph<T, Double> graph,
                                                          BufferedReader br,
                                                          String[] firstLine,
                                                          Function<String, T> converter) throws IOException {
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
    private <T> Graph<T, Void> populateGraphWithoutWeights(Graph<T, Void> graph,
                                                           BufferedReader br,
                                                           String[] firstLine,
                                                           Function<String, T> converter) throws IOException {
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
