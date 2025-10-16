package org.example.core.interfaces;

import java.util.Set;

public interface Graph<V, U> {
    void addVertex(V vertex);
    void removeVertex(V vertex);
    void addRelation(V first, V second, U weight);
    void removeRelation(V first, V second);
    U getRelation(V first, V second);
    boolean hasRelation(V first, V second);
    int degreeOf(V vertex);
    boolean hasVertex(V vertex);
    Set<V> getNeightbours(V vertex);
    Set<V> vertexSet();
    Graph<V, U> getTransposed();
    Graph<V, U> constructGraph();
    int size();
    void showVertex();
    void showRelations();
    boolean isDirected();
    boolean isEmpty();
    boolean isWeighted();
    enum GraphType {
        DIRECTED("D"),
        UNDIRECTED("ND");
        final String name;
        GraphType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
        public static GraphType fromValue(String text) {
            for (GraphType type : GraphType.values()) {
                if (type.name.equalsIgnoreCase(text)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Valor de tipo de grafo inválido: " + text);
        }
    }
}
