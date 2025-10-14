package org.example.core.implementations;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.example.core.abstracts.GraphBase;
import org.example.core.interfaces.Graph;
/**
 * Implementação da estrutura de dados de um grafo baseada na representação por Listas de Adjacência.
 * <p>
 * A estrutura interna utiliza um {@code HashMap} aninhado. O mapa principal associa
 * cada vértice a um segundo mapa, que funciona como sua lista de adjacências.
 * Este segundo mapa, por sua vez, mapeia cada vértice vizinho ao peso da aresta
 * correspondente.
 * <p>
 * O peso da aresta é encapsulado em um {@code Optional}, o que confere flexibilidade
 * para que a classe suporte tanto grafos ponderados quanto não-ponderados com a mesma
 * implementação. Esta abordagem é otimizada para grafos esparsos, tornando-a
 * ideal para aplicações como análise de redes sociais.
 *
 * @param <V> O tipo que representa os vértices do grafo (ex: String, Integer).
 * @param <U> O tipo que representa o peso das arestas (ex: Double).
 *
 * @see org.example.core.interfaces.Graph
 * @author Angelo - Implementação
 * @author Camila - Documentação e Revisão
 */
public class AdjacencyListGraph<V, U>
        extends GraphBase<V, U> {
    //type restricted attributes
    private final HashMap<V, HashMap<V, Optional<U>>> vertex;
    //common methods
    public AdjacencyListGraph(GraphType type, boolean isWeighted){
        super(type, isWeighted);
        vertex = new HashMap<>();
    }
    //type restricted methods
    /// Implementação em {@link AdjacencyListGraph}.
    ///
    /// @see Graph#getRelation(Object, Object)
    /// @param first fonte
    /// @param second destino
    /// @return  o valor associado ao valor da chave no hashmap caso exista. Se não, retorna nulo

    @Override
    public U getRelation(V first, V second) {
        Optional<U> weight = vertex.get(first).get(second);
        return weight.orElse(null);
    }
/// Implementação em {@link AdjacencyListGraph}.
/// adiciona um vértice de qualquer tipo ao grafo caso ele não tenha sido adicionado
///
/// @see Graph#addVertex(Object)
/// @param vertex vértice a ser adicionado

    @Override
    public void addVertex(V vertex) {
        if (!this.vertex.containsKey(vertex)) {
            this.vertex.put(vertex, new HashMap<>());
        }
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// remove um vértice de qualquer tipo no grafo.
    /// {@link HashMap} trata remoção de vertices inexistentes.
    ///
    /// @see Graph#removeVertex(Object)
    /// @param vertex vertice a ser removido

    @Override
    public void removeVertex(V vertex) {
        this.vertex.remove(vertex);
    }
    /// adiciona uma aresta a partir de dois vértices existentes.
    ///
    /// esse método com 2 atributos é para grafos sem peso (tratado na primeira condicional)
    ///
    /// os vértices precisam estar no grafo.
    ///
    /// caso o grafo não seja digrafo, adiciona a relação inversa
    ///
    /// @param first vértice fonte
    /// @param second vértice destino
    public void addRelation(V first, V second){
        if(isWeighted())
            return;
        if(!hasVertex(first)||!hasVertex(second))
            return;

        this.vertex.get(first).put(second, Optional.empty());
        if(!isDirected())
            this.vertex.get(second).put(first, Optional.empty());
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    ///  método que adiciona uma aresta a grafos com peso.
    ///
    /// caso qualquer um dos vértices não tenha sido adicionado, não procede.
    ///
    /// considera grafos não direcionados, adicionando a relação inversa no caso.
    /// @see Graph#addRelation(Object, Object, Object)
    /// @param first vértice fonte
    /// @param second vértice de destino
    /// @param weight peso
    @Override
    public void addRelation(V first, V second, U weight) {
        if(!hasVertex(first)||!hasVertex(second))
            return;

        this.vertex.get(first).put(second, Optional.ofNullable(weight));
        if(!isDirected())
            this.vertex.get(second).put(first, Optional.ofNullable(weight));

    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// remove a relaçõa entre dois vértices a partir do hashmap.
    /// se o grafo não for dirigido, remove a relação inversa.
    /// @see Graph#removeRelation(Object, Object)
    /// @param first vértice fonte
    /// @param second vértice alvo
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
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// dado um vértice, retorna o tamanho do valor associado à chave no hashmap
    /// @see Graph#degreeOf(Object)
    /// @param vertex vértice a ser analisado
    /// @return o tamanho da lista que o vértice aponta
    @Override
    public int degreeOf(V vertex) {
        return this.vertex.get(vertex).size();
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// dado um vértice, verifica se ele existe no hashmap
    /// @see Graph#hasVertex(Object)
    /// @param vertex vértice a ser analisado
    ///
    /// @return true se o vértice existe no grafo, false caso contrário
    @Override
    public boolean hasVertex(V vertex) {
        return this.vertex.containsKey(vertex);
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// dado um vértice, retorna o conjunto de vértices apontados por este no hashmap
    ///
    /// @see Graph#getNeightbours(Object)
    /// @param vertex vértice a ser analisado
    ///
    /// @return um {@link Set} de vértices apontados pelo vértice de entrada
    @Override
    public Set<V> getNeightbours(V vertex) {
        return this.vertex.get(vertex).keySet();
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// retorna os vértices do grafo a partir do hashmap
    /// @see Graph#vertexSet()
    /// @return um {@link Set} com todos os vértices no grafo de lista de adjcencias
    @Override
    public Set<V> vertexSet() {
        return vertex.keySet();
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    ///
    ///
    /// @see Graph#constructGraph()
    /// @return um objeto da classe que implementa {@link Graph}
    @Override
    public Graph<V, U> constructGraph() {
        return new AdjacencyListGraph<>(type, isWeighted());
    }
    /// Implementação em {@link AdjacencyListGraph}
    /// @see Graph#size()
    ///
    /// @return a quantidade de vértices do grafo
    @Override
    public int size() {
        return this.vertex.size();
    }
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// Percorre o hashmap de vértices e arestas, exibindo a fonte, o alvo e as relações no terminal.
    ///
    /// @see Graph#showRelations()
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
    /// Implementação em {@link AdjacencyListGraph}
    ///
    /// Percorre o hashmap de vértices, printando eles.
    ///
    /// @see Graph#showRelations()
    @Override
    public void showVertex() {
        vertex.keySet().forEach(
                v-> System.out.println(v.toString())
        );
    }


}
