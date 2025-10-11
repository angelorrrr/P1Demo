package org.example.core.abstracts;

import org.example.core.implementations.AdjacencyListGraph;
import org.example.core.interfaces.Graph;
/// Classe
public abstract class GraphBase<V, U>
        implements Graph<V, U> {
    //common attributes
    protected final GraphType type;
    private final boolean isWeighted;

    protected GraphBase(GraphType type, boolean isWeighted) {
        this.type = type;
        this.isWeighted = isWeighted;
    }
    @Override
    public boolean isDirected() {
        return type==GraphType.DIRECTED;
    }
    @Override
    public boolean isWeighted(){
        return isWeighted;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public Graph<V, U> getTransposed() {
        Graph<V, U> transposed = constructGraph();

        for(V vertex : vertexSet()){
            transposed.addVertex(vertex);
        }

        for(V source : vertexSet()){
            for(V neighbour : getNeightbours(source)){
                U weight = getRelation(source, neighbour);
                transposed.addRelation(neighbour, source, weight);
            }
        }

        return transposed;
    }
}
