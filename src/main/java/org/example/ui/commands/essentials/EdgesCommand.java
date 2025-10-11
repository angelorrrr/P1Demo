package org.example.ui.commands.essentials;

import org.example.core.interfaces.Graph;
import org.example.ui.commands.Command;
import org.example.ui.Context;

public class EdgesCommand implements Command {
    @Override
    public void execute() {
        Graph<Object, Object> graph = (Graph<Object, Object>) Context.getInstance().getGraph();
        System.out.println("arestas do grafo:");
        graph.showRelations();
        appendHistory("");
    }

    @Override
    public String description() {
        return "Comando arestas";
    }
}
