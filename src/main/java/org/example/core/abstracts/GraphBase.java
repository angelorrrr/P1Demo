package org.example.core.abstracts;

import org.example.core.implementations.AdjacencyListGraph;
import org.example.core.interfaces.Graph;
/// Classe que implementa alguns métodos compartilhados entre as implementações.
///
/// Essa classe implementa o método getTransposed através de uma factory constructGraph(), abstrata.
/// A factory constructGraph() simplesmente retorna um objeto da classe concreta que o implementa, permitindo assim a
/// volatilidade da criação do grafo transposto entre as classes que implementam {@link Graph}.
///
/// Classe
public abstract class GraphBase<V, U>
        implements Graph<V, U> {
    //common attributes
    protected final GraphType type;
    private final boolean isWeighted;
    /// Constructor da classe GraphBase, comum a todas as classes filhas

    protected GraphBase(GraphType type, boolean isWeighted) {
        this.type = type;
        this.isWeighted = isWeighted;
    }
    /// cálculo comum a todas as classes de grafo paara verificar o tipo
    ///
    /// @return true se for {@link org.example.core.interfaces.Graph.GraphType#DIRECTED}, falso caso contrário

    @Override
    public boolean isDirected() {
        return type==GraphType.DIRECTED;
    }
    /// cálculo comum a todas as implementações para saber se um grafo tem peso ou não.
    ///
    /// getter de {@link GraphBase#isWeighted}
    ///
    /// @return {@link GraphBase#isWeighted}
    @Override
    public boolean isWeighted(){
        return isWeighted;
    }
    /// cálculo comum a todas as implementações para saber se um grafo tem elementos.
    ///
    /// @return true se o tamanho for 0, false caso contrário
    @Override
    public boolean isEmpty() {
        return size()==0;
    }
    /// método comum a todas as implementações.
    ///
    /// <p>O algoritmo de produção do grafo transposto é bem simples:</p>
    ///
    /// <pre><code>
    ///
    /// funcao getTransposed():
    ///     g' = constructGraph()
    ///     para cada vertice nesse grafo:
    ///         g'.addVertex(vertice)
    ///
    ///     para cada fonte nesse grafo:
    ///         para cada vizinho da fonte:
    ///             aresta = getRelation(fonte, vizinho) //aresta nesse grafo
    ///             g'.addRelation(vizinho, fonte, aresta) //aqui ocorre a transposição
    /// </code></pre>
    ///
    /// @return Um grafo do mesmo tipo do objeto que invoca este método e com os mesmos vértices, mas com as arestas invertidas.
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