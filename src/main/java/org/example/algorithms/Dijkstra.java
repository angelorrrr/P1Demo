package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.*;
public class Dijkstra {
    private final Map<Object, Double> distances = new HashMap<>();
    private final Map<Object, Object> predecessors = new HashMap<>();
    private final PriorityQueue<Object> queue;
    private final Object v1;
    private boolean isSolvable;

    public Dijkstra(final Graph<Object, Object> graph, final Object v1) {
        this.v1 = v1;
        this.queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Object vertex : graph.vertexSet()) {
            distances.put(vertex, Double.MAX_VALUE);
            predecessors.put(vertex, null);
        }

        distances.put(v1, 0.0);
        queue.add(v1);

        while (!queue.isEmpty()) {
            Object current = queue.poll();

            for (Object neighbor : graph.getNeightbours(current)) {
                try {
                    double edgeWeight = (Double) graph.getRelation(current, neighbor);
                    double newDist = distances.get(current) + edgeWeight;

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        predecessors.put(neighbor, current);
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                } catch (ClassCastException | NullPointerException e) {
                    isSolvable = false;
                    return;
                }
            }
        }

        isSolvable = true;
    }

    public List<Object> constructSet(Object v2) {
        List<Object> path = new ArrayList<>();
        for (Object at = v2; at != null; at = predecessors.get(at)) {
            path.add(at);
            if (at.equals(v1)) break;
        }

        Collections.reverse(path);

        if (!isSolvable || !path.getFirst().equals(v1)) {
            System.out.println("impossível usar Dijkstra nesse grafo, ou não deu pra achar um caminho");
            return null;
        }

        return path;
    }
}
