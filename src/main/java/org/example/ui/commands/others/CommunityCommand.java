package org.example.ui.commands.others;

import org.example.algorithms.Kosaraju;
import org.example.core.interfaces.Graph;
import org.example.ui.commands.Command;
import org.example.ui.Context;

public class CommunityCommand implements Command {
    @Override
    public void execute() {
        Graph<?, ?> graph = Context.getInstance().getGraph();
        Kosaraju<?, ?> solution = new Kosaraju<>(graph);
        solution.showCommunities();
        appendHistory(solution.toString());
    }

    @Override
    public String description() {
        return "Comando comunidade";
    }
}
