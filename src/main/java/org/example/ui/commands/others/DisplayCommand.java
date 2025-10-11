package org.example.ui.commands.others;

import org.example.core.interfaces.Graph;
import org.example.io.GraphView;
import org.example.ui.commands.Command;
import org.example.ui.Context;

public class DisplayCommand implements Command {
    @Override
    public void execute(){
        Graph<?, ?> graph = Context.getInstance().getGraph();
        String result = GraphView.processGraph(graph);
        appendHistory(result);
    }

    @Override
    public String description() {
        return "comando display";
    }
}
