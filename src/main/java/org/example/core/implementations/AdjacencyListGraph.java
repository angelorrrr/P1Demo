package org.example.core.implementations;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.example.core.abstracts.GraphBase;
import org.example.core.interfaces.Graph;

public class AdjacencyListGraph<T, U>
        extends GraphBase<T, U> {
    //type restricted attributes
    private final HashMap<T, HashMap<T, Optional<U>>> vertex;
    //common methods
    public AdjacencyListGraph(GraphType type, boolean isWeighted){
        super(type, isWeighted);
        vertex = new HashMap<>();
    }
    //type restricted methods

    @Override
    public U getRelation(T first, T second) {
        Optional<U> weight = vertex.get(first).get(second);
        return weight.orElse(null);
    }

    @Override
    public void addVertex(T vertex) {
        if (!this.vertex.containsKey(vertex)) {
            this.vertex.put(vertex, new HashMap<>());
        }
    }
    @Override
    public void removeVertex(T vertex) {
        this.vertex.remove(vertex);
    }
    public void addRelation(T first, T second){
        if(isWeighted())
            return;
        if(!hasVertex(first)||!hasVertex(second))
            return;

        this.vertex.get(first).put(second, Optional.empty());
        if(!isDirected())
            this.vertex.get(second).put(first, Optional.empty());
    }
    @Override
    public void addRelation(T first, T second, U weight) {
        if(!hasVertex(first)||!hasVertex(second))
            return;

        this.vertex.get(first).put(second, Optional.ofNullable(weight));
        if(!isDirected())
            this.vertex.get(second).put(first, Optional.ofNullable(weight));

    }
    @Override
    public void removeRelation(T first, T second) {
        if(hasVertex(first)&&hasVertex(second))
            vertex.get(first).remove(second);
    }
    @Override
    public boolean hasRelation(T first, T second) {
        return this.vertex.get(first).containsKey(second);
    }
    @Override
    public int degreeOf(T vertex) {
        return this.vertex.get(vertex).size();
    }
    @Override
    public boolean hasVertex(T vertex) {
        return this.vertex.containsKey(vertex);
    }
    @Override
    public Set<T> getNeightbours(T vertex) {
        return this.vertex.get(vertex).keySet();
    }

    @Override
    public Set<T> vertexSet() {
        return vertex.keySet();
    }

    @Override
    public int size() {
        return this.vertex.size();
    }
    @Override
    public void showRelations() {
        vertex.forEach((src, edges) ->
                edges.forEach((dst, weight) ->
                        System.out.printf("%s --(%s)--> %s%n",
                                src,
                                weight.map(Object::toString).orElse(""),
                                dst)
                )
        );
    }
    @Override
    public void showVertex() {
        vertex.keySet().forEach(
                v-> System.out.println(v.toString())
        );
    }

    @Override
    public Graph<T, U> getTransposed() {
        Graph<T, U> transposed = new AdjacencyListGraph<>(GraphType.DIRECTED, isWeighted());

        for(T vertex : vertexSet()){
            transposed.addVertex(vertex);
        }

        for(T source : vertexSet()){
            for(T neighbour : getNeightbours(source)){
                U weight = getRelation(source, neighbour);
                transposed.addRelation(neighbour, source, weight);
            }
        }

        return transposed;
    }
}
