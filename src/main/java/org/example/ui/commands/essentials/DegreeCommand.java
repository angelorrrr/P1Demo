package org.example.ui.commands.essentials;

import org.example.core.interfaces.Graph;
import org.example.ui.Menu;
import org.example.ui.commands.Command;
import org.example.ui.Context;

import java.util.Optional;
import java.util.Scanner;

public class DegreeCommand implements Command {
    @Override
    public void execute() {
        Scanner sc = Menu.getInstance().getScanner();
        Optional<Object> v = Context.getInstance().selectVertex("escolha o vértice:", sc);
        if(v.isEmpty()){
            return;
        }
        Graph<Object, Object> graph = (Graph<Object, Object>) Context.getInstance().getGraph();
        int grau = graph.degreeOf(v.get());
        System.out.printf("d(%s): %d\n", v.get(), grau);
        appendHistory(String.valueOf(grau));
    }

    @Override
    public String description() {
        return "Comando grau";
    }
}
