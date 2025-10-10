package org.example.core.abstracts;

import org.example.core.interfaces.Graph;

public abstract class GraphBase<T, U>
        implements Graph<T, U> {
    //common attributes
    private final GraphType type;
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
}
