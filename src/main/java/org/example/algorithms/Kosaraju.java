package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.*;
import java.util.function.Consumer;

public class Kosaraju<V, U> extends HashMap<String, List<V>> {
    public Kosaraju(Graph<V, U> graph){
        Stack<V> stack = new Stack<>();
        DFS.search(graph, stack::push, v->{});


        Graph<V, U> transposed = graph.getTransposed();
        Set<V> visited = new HashSet<>();

        int counter = 1;
        while (!stack.empty()) {
            V vertex = stack.pop();
            if(visited.contains(vertex)) continue;

            List<V> list = new LinkedList<>();
            Consumer<V> preOrderAction = (v)->{
                visited.add(v);
                list.add(v);
            };
            DFS.dfs(vertex, visited, graph, preOrderAction, (v)->{});

            put("comunidade "+counter+": ", list);
            counter++;
        }

    }
    public void showCommunities(){
        System.out.println(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (java.util.Map.Entry<String, List<V>> entry : entrySet()) {
            sb.append(entry.getKey()).append(": ");
            sb.append(entry.getValue().toString()).append("\n");

        }
        return sb.toString();
    }
}