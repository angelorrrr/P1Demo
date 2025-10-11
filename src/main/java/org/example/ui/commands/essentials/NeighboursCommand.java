package org.example.ui.commands.essentials;

import org.example.core.interfaces.Graph;
import org.example.ui.Menu;
import org.example.ui.commands.Command;
import org.example.ui.Context;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class NeighboursCommand implements Command {
    @Override
    public void execute() {
        Scanner sc = Menu.getInstance().getScanner();
        Optional<Object> v = Context.getInstance().selectVertex("escolha o vértice:", sc);
        if(v.isEmpty()){
            return;
        }
        Object v1 = v.get();
        Graph<Object, Object> graph = (Graph<Object, Object>) Context.getInstance().getGraph();
        Collection<Object> vizinhos = graph.getNeightbours(v1);
        String answer = String.format("vizinhos de %s: %s\n", v1, vizinhos);
        System.out.printf(answer);
        appendHistory(answer);
    }

    @Override
    public String description() {
        return "Comando vizinhos";
    }
}
