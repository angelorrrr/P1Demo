package org.example.core.implementations;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.example.core.abstracts.GraphBase;
import org.example.core.interfaces.Graph;
public class AdjacencyListGraph<V, U>
        extends GraphBase<V, U> {
    private final HashMap<V, HashMap<V, Optional<U>>> vertex;
    public AdjacencyListGraph(GraphType type, boolean isWeighted){
        super(type, isWeighted);
        vertex = new HashMap<>();
    }
    @Override
    public U getRelation(V first, V second) {
        Optional<U> weight = vertex.get(first).get(second);
        return weight.orElse(null);
    }
    @Override
    public void addVertex(V vertex) {
        if (!this.vertex.containsKey(vertex)) {
            this.vertex.put(vertex, new HashMap<>());
        }
    }

    @Override
    public void removeVertex(V vertex) {
        this.vertex.remove(vertex);
    }
    public void addRelation(V first, V second){
        if(isWeighted())
            return;
        if(!hasVertex(first)||!hasVertex(second))
            return;

        this.vertex.get(first).put(second, Optional.empty());
        if(!isDirected())
            this.vertex.get(second).put(first, Optional.empty());
    }
    @Override
    public void addRelation(V first, V second, U weight) {
        if(!hasVertex(first)||!hasVertex(second))
            return;

        this.vertex.get(first).put(second, Optional.ofNullable(weight));
        if(!isDirected())
            this.vertex.get(second).put(first, Optional.ofNullable(weight));

    }
    @Override
    public void removeRelation(V first, V second) {
        if(!hasVertex(first)||!hasVertex(second))
            return;
        vertex.get(first).remove(second);
        if(!isDirected())
            vertex.get(second).remove(first);
    }
    @Override
    public boolean hasRelation(V first, V second) {
        return this.vertex.get(first).containsKey(second);
    }
    @Override
    public int degreeOf(V vertex) {
        return this.vertex.get(vertex).size();
    }
    @Override
    public boolean hasVertex(V vertex) {
        return this.vertex.containsKey(vertex);
    }
    @Override
    public Set<V> getNeightbours(V vertex) {
        return this.vertex.get(vertex).keySet();
    }
    @Override
    public Set<V> vertexSet() {
        return vertex.keySet();
    }
    @Override
    public Graph<V, U> constructGraph() {
        return new AdjacencyListGraph<>(type, isWeighted());
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


}
