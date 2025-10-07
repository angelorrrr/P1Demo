package org.example.graph.interfaces;

import java.util.Optional;
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
}
