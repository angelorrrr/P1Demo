package org.example.ui;

import org.example.Main;
import org.example.core.interfaces.Graph;
import org.example.io.TXTReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Context {
    private static Context instance;
    private final Graph<?, ?> graph;

    public synchronized static Context getInstance(){
        if (instance == null) {
            try {
                instance = new Context();
            } catch (IOException e) {
                //fazer uma exceção própria depois
                throw new RuntimeException("não deu de ler o grafo");
            }
        }
        return instance;
    }
    private Context() throws IOException {
        TXTReader reader = new TXTReader(Main.filePath);
        this.graph = reader.getGraph();
    }
    public Graph<?, ?> getGraph() {
        return graph;
    }
    public Optional<Object> selectVertex(String mensagem, Scanner sc) {
        List<Object> vertices = new ArrayList<>(graph.vertexSet());
        System.out.println("\n" + mensagem);
        for (int i = 0; i < vertices.size(); i++) {
            System.out.printf("%d: %s\n", i + 1, vertices.get(i));
        }
        System.out.print("escolha o vértice: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao >= 1 && opcao <= vertices.size()) {
            return Optional.of(vertices.get(opcao - 1));
        } else {
            System.out.println("inválido.");
        }
        return Optional.empty();
    }
}
