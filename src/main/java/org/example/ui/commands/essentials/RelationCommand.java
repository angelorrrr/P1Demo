package org.example.ui.commands.essentials;

import org.example.core.interfaces.Graph;
import org.example.ui.Menu;
import org.example.ui.commands.Command;
import org.example.ui.Context;

import java.util.Optional;
import java.util.Scanner;

public class RelationCommand implements Command {
    @Override
    public void execute() {
        Scanner sc = Menu.getInstance().getScanner();
        System.out.println("selecione os dois vértices:");
        Optional<Object> v1 = Context.getInstance().selectVertex("primeiro", sc);
        Optional<Object> v2 = Context.getInstance().selectVertex("segundo", sc);
        if(v1.isEmpty() || v2.isEmpty())
            return;
        Graph<Object, Object> graph = (Graph<Object, Object>) Context.getInstance().getGraph();
        boolean adj = graph.hasRelation(v1.get(), v2.get());
        String answer = String.format("%s e %s %ssão adjacentes.\n", v1.get(), v2.get(), adj ? "" : "não ");
        System.out.printf(answer);
        appendHistory(answer);
    }

    @Override
    public String description() {
        return "Comando relacao";
    }
}
