package org.example.algorythms;

import org.example.graph.interfaces.Graph;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class Kosaraju<U, V> extends HashMap<String, Stack<U>> {
    public Kosaraju(Graph<U, V> graph){
        Stack<U> stack = new Stack<>();
        HashMap<U, Boolean> visited = new HashMap<>();
        for(U vertex : graph.vertexSet()){
            visited.put(vertex, Boolean.FALSE);
        }
        for(U vertex : graph.vertexSet()){
            fillOrder(vertex, visited, stack, graph);
        }

        Graph<U, V> transposed = graph.getTransposed();

        for(U vertex : transposed.vertexSet()){
            visited.put(vertex, Boolean.FALSE);
        }
        int counter = 1;
        while (!stack.empty()){
            U vertex = stack.pop();
            if (!visited.get(vertex)) {
                Stack<U> sccStack = new Stack<>();
                dfsCollect(vertex, visited, sccStack, transposed);
                put("comunidade "+counter+": ", sccStack);
                counter++;
            }
        }
    }

    private void dfsCollect(U vertex, HashMap<U, Boolean> visited, Stack<U> sccStack, Graph<U, V> graph) {
        visited.put(vertex, Boolean.TRUE);
        sccStack.push(vertex);
        for (U neighbour : graph.getNeightbours(vertex)) {
            if (!visited.get(neighbour)) {
                dfsCollect(neighbour, visited, sccStack, graph);
            }
        }
    }

    private void fillOrder(U vertex, HashMap<U, Boolean> visited, Stack<U> stack, Graph<U, V> graph){
        visited.put(vertex, Boolean.TRUE);
        for(U neighbour : graph.getNeightbours(vertex)){
            if(!visited.get(neighbour)){
                fillOrder(neighbour, visited, stack, graph);
            }
        }
        stack.push(vertex);
    }

    public void showCommunities(){
        for (java.util.Map.Entry<String, Stack<U>> entry : entrySet()) {
            System.out.println(entry.getKey() + entry.getValue().toString());
        }
    }
}
