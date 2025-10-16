package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.*;
import java.util.function.Consumer;
///
/// <b>Classe de utilidade {@link  DFS}</b>
///
/// Objetivo: receber um {@link Graph} de qualquer natureza
/// Executar o pseudocódigo:
/// <P>Pseudocódigo DFS
/// </P>
///
/// <pre>
/// <code>
/// //ponto de entrada do grafo
/// funcao dfs(Grafo grafo, Ação preOrdem, Ação posOrdem):
///     Conjunto visitados
///     para cada vertice em grafo:
///         se vertice nao foi visitado:
///             dfs(vertice, visitados, grafo, preOrdem, posOrdem)
/// //procedimento recursivo aos vizinhos
/// funcao dfs(Vertice vertice, Conjunto visitados, Grafo grafo, Ação preOrdem, AçãoposOrdem):
///     visitar vertice
///     executar preOrdem
///     para cada vizinho de vertice:
///         se vizinho nao foi visitado:
///             dfs(vizinho, visitados, grafo, preOrdem, posOrdem)
///     executar posOrdem
/// </code></pre>
///
/// Para usar, basta repassar o grafo e o que você quer fazer com os vértices
/// em inordem ou posordem como {@link Consumer}.
///
/// Existe outro procedimento público, que aplica um dfs a um vértice do grafo
/// (Util para a classe {@link  Kosaraju})`
public class DFS{
    /// O objetivo desse procedimento é percorrer um grafo a partir de um
    /// vértice
    /// @since 1.4
    /// @param graph o grafo a ser percorrido
    /// @param vertex o vértice que inicia a busca
    /// @param preOrder procedimento que usa cada vértice em pré-ordem
    /// @param postOrder procedimento que usa cada vértice em pós ordem

    public static <V, U> void search(Graph<V, U> graph, V vertex, Consumer<V> preOrder, Consumer<V> postOrder) {
        Set<V> visited = new HashSet<>();
        dfs(vertex, visited, graph, preOrder, postOrder);
    }

    /// O objetivo desse procedimento é percorrer o grafo em dfs, permitindo
    /// ações com os vértices em in-ordem ou pos-ordem
    /// @since 1.0
    /// @param graph o grafo a ser percorrido
    /// @param preOrder procedimento que usa cada vértice em pre-ordem
    /// @param postOrder procedimento que usa cada vértice em pos-ordem
    public static <V, U>void search(Graph<V,U> graph, Consumer<V> preOrder, Consumer<V> postOrder){
        Set<V> visited = new HashSet<>();
        for(V vertex : graph.vertexSet()){
            if(!visited.contains(vertex)){
                dfs(vertex, visited, graph, preOrder, postOrder);
            }
        }
    }

    ///
    /// Esse é o procedimento privado que é a base recursiva dos dfs acessíveis
    /// @param graph o grafo a ser percorrido
    /// @param vertex o vértice que inicia a busca
    /// @param preOrder procedimento que usa cada vértice em pre-ordem
    /// @param postOrder procedimento que usa cada vértice em pos-ordem
    /// @param visited conjunto de vértices já visitados
    ///
    /// @since 1.0
    ///
    public static <V, U> void dfs(V vertex, Set<V> visited, Graph<V, U> graph, Consumer<V> preOrder, Consumer<V> postOrder) {
        visited.add(vertex);
        preOrder.accept(vertex);
        for (V neighbour : graph.getNeightbours(vertex)) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, visited, graph, preOrder, postOrder);
            }
        }

        postOrder.accept(vertex);
    }

}