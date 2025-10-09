package org.example.core.interfaces;

import java.util.Set;


public interface Graph<T, U> {
    //basic functionalities
    void addVertex(T vertex);
    void removeVertex(T vertex);
    void addRelation(T first, T second, U weight);
    void removeRelation(T first, T second);
    U getRelation(T first, T second);
    //algorithm functionalities
    boolean hasRelation(T first, T second);
    int degreeOf(T vertex);
    boolean hasVertex(T vertex);
    Set<T> getNeightbours(T vertex);
    Set<T> vertexSet();
    Graph<T, U> getTransposed();
    //general atributes
    int size();
    void showVertex();
    void showRelations();
    boolean isDirected();
    boolean isEmpty();

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
