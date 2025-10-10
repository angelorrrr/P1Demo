package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.*;
import java.util.function.Consumer;

public class DFS{

    //aqui só percorre um caminho que compreenda o vertice de entrada.
    //não há necessidade do for para os outros vertices, porque ele iria para os outros
    //componentes
    public static <V, U> void search(Graph<V, U> graph, V vertex, Consumer<V> preOrder, Consumer<V> postOrder) {
        Set<V> visited = new HashSet<>();
        visited.add(vertex);
        dfs(vertex, visited, graph, preOrder, postOrder);
    }

    public static <V, U>void search(Graph<V,U> graph, Consumer<V> preOrder, Consumer<V> postOrder){
        Set<V> visited = new HashSet<>();
        for(V vertex : graph.vertexSet()){
            if(!visited.contains(vertex)){
                dfs(vertex, visited, graph, preOrder, postOrder);
            }
        }
    }
    private static <V, U> void dfs(V vertex, Set<V> visited, Graph<V, U> graph, Consumer<V> preOrder, Consumer<V> postOrder) {
        visited.add(vertex);
        preOrder.accept(vertex);
        for (V neighbour : graph.getNeightbours(vertex)) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, visited, graph, preOrder, postOrder);
            }
        }

        postOrder.accept(vertex);
    }

}