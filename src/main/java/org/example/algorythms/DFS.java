package org.example.algorythms;

import org.example.graph.interfaces.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DFS<V, U> {

    public DFS() {
    }

    public List<List<V>> findConnectedComponents(Graph<V, U> graph) {
        List<List<V>> allComponents = new ArrayList<>();
        HashMap<V, Boolean> visitedVertexes = new HashMap<>();
        
        for (V vertex : graph.vertexSet()) {
            visitedVertexes.put(vertex, Boolean.FALSE);
        }

        for (V vertex : visitedVertexes.keySet()) {
            if (!visitedVertexes.get(vertex)) {
                List<V> currentComponent = new ArrayList<>();
        
                dfs(vertex, visitedVertexes, graph, currentComponent);
                allComponents.add(currentComponent);
            }
        }
        
        return allComponents;
    }

    private void dfs(V vertex, HashMap<V, Boolean> visitedVertices, Graph<V, U> graph, List<V> currentComponent) {
        visitedVertices.put(vertex, Boolean.TRUE);
        currentComponent.add(vertex);

        if (graph.getNeightbours(vertex) != null) {
            for (V neighbour : graph.getNeightbours(vertex)) {
                if (!visitedVertices.get(neighbour)) {
                    dfs(neighbour, visitedVertices, graph, currentComponent);
                }
            }
        }
    }
}