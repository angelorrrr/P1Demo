package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

public class Kosaraju<U, V> extends HashMap<String, Stack<U>> {
    public Kosaraju(Graph<U, V> graph){
        Stack<U> stack = new Stack<>();
        for(U vertex : graph.vertexSet())
            DFS.search(graph, vertex, v->{}, stack::push);


        Graph<U, V> transposed = graph.getTransposed();
        Set<U> visited = new HashSet<>();

        int counter = 1;
        while (!stack.empty()) {
            U vertex = stack.pop();
            if(visited.contains(vertex)) continue;

            Stack<U> sccStack = new Stack<>();
            DFS.search(transposed, vertex, (v)->{
                visited.add(v);
                sccStack.push(v);
            }, v->{});

            put("comunidade "+counter+": ", sccStack);
            counter++;
        }

    }
    public void showCommunities(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (java.util.Map.Entry<String, Stack<U>> entry : entrySet()) {
            sb.append(entry.getKey()).append(": ");
            sb.append(entry.getValue().toString()).append("\n");

        }
        return sb.toString();
    }
}