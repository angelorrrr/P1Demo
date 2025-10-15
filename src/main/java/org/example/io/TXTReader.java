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
                populateGraph((Graph<Integer, Double>) graph, br, firstLine, Integer::parseInt);
            }catch (NumberFormatException ignored) {
                populateGraph((Graph<String, Double>)graph, br, firstLine, Function.identity()); // Function.identity() for String
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
                                                          String[] firstLine,
                                                          Function<String, T> converter) throws IOException {
        T src = converter.apply(firstLine[0].trim());
        T dest = converter.apply(firstLine[1].trim());
        double weight = weightGetter(firstLine);
        graph.addVertex(src);
        graph.addVertex(dest);
        graph.addRelation(src, dest, weight);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            src = converter.apply(parts[0].trim());
            dest = converter.apply(parts[1].trim());
            weight = weightGetter(parts);
            graph.addVertex(src);
            graph.addVertex(dest);
            graph.addRelation(src, dest, weight);
        }
    }

}
