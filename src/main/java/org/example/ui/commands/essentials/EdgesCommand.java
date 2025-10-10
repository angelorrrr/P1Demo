package org.example.ui.commands.essentials;

import org.example.core.interfaces.Graph;
import org.example.ui.commands.Command;
import org.example.ui.Context;

public class EdgesCommand implements Command {
    @Override
    public void execute() {
        Graph<Object, Object> graph = (Graph<Object, Object>) Context.getInstance().getGraph();
        System.out.println("arestas do grafo:");
        StringBuilder answer = new StringBuilder();
        for (Object v1 : graph.vertexSet()) {
            for (Object v2 : graph.getNeightbours(v1)) {
                if (!graph.isDirected() && v1.toString().compareTo(v2.toString()) > 0) continue;
                Object rel = graph.getRelation(v1, v2);
                String edge = String.format("%s --(%s)--> %s\n", v1, rel != null ? rel : "", v2);
                System.out.println(edge);
                answer.append(edge);
            }
        }
        appendHistory(answer.toString());
    }

    @Override
    public String description() {
        return "Comando arestas";
    }
}
