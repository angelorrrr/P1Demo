package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.HashMap;

public class DFS<V, U> {
    public DFS(Graph<V, U> graph){
        HashMap<V, Boolean> visitedVertexes = new HashMap<>();
        for (V vertex : graph.vertexSet()){
            visitedVertexes.put(vertex, Boolean.FALSE);
        }
        for(V vertex : visitedVertexes.keySet()){
            if (!visitedVertexes.get(vertex)) {
                dfs(vertex, visitedVertexes, graph);
            }
        }
    }
    private void dfs(V vertex, HashMap<V, Boolean> visitedVertices, Graph<V, U> graph){
        visitedVertices.put(vertex, Boolean.TRUE);
        System.out.println("visitando: "+vertex);
        for(V neighbour : graph.getNeightbours(vertex)){
            if(!visitedVertices.get(neighbour)){
                dfs(neighbour, visitedVertices, graph);
            }
        }
    }
}
