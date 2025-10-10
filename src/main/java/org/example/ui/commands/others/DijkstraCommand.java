package org.example.ui.commands.others;

import org.example.algorithms.Dijkstra;
import org.example.core.interfaces.Graph;
import org.example.ui.Menu;
import org.example.ui.commands.Command;
import org.example.ui.Context;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DijkstraCommand implements Command {
    @Override
    public void execute() {
        Scanner sc = Menu.getInstance().getScanner();
        System.out.println("Escolha a fonte");
        Optional<Object> v1 = Context.getInstance().selectVertex("fonte", sc);
        if(v1.isEmpty()){
            System.out.println("invalido");
            return;
        }
        Dijkstra dijkstra = new Dijkstra((Graph<Object, Object>) Context.getInstance().getGraph(), v1.get());
        Optional<Object> target = Context.getInstance().selectVertex("alvo", sc);
        if(target.isEmpty()){
            System.out.println("invalido");
            return;
        }
        List<Object> path = dijkstra.constructSet(target.get());
        System.out.println(path.toString());
        appendHistory(path.toString());
    }

    @Override
    public String description() {
        return "Comando dijkstra";
    }
}
