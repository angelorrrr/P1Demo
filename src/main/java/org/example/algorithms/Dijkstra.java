package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.*;
///
/// <b>Algoritmo de {@link Dijkstra}</b>
///
/// Objetivo: dado um grafo g(u, v) com pesos e um par de vértices,
/// encontrar um caminho entre o par que tenha o menor custo possível.
/// <P>O algoritmo está representado no pseudocódigo abaixo:</P>
///
/// <pre>
/// <code>
/// funcao dijkstra(grafo g, vertice fonte, vertice alvo):
///     para cada vertice v de g:
///         distancia(v) = infinito
///         predecessor(v) = -1;
///
///     distancia(fonte) = 0
///     Conjunto q = todos os vertices de g
///     enquanto(q nao vazio):
///         u = vertice com menor distancia
///         se distancia(u) igual a infinito
///             continue
///         remova u de q
///
///         para cada vizinho v de u:
///             alt = distancia(u)+aresta(u, v)
///             se alt é menor que distancia(v)
///                 distancia(v)=alt
///                 predecessor(v)=u
///     retorne distancia[], predecessor[]
/// </code></pre>
///
/// Essa classe construirá um objeto capaz de construir o menor caminho
/// entre qualquer par de vértices, bastando utilizar o procedimento {@link Dijkstra#constructSet(Object)}
public class Dijkstra {
    private final Map<Object, Object> predecessors = new HashMap<>();
    private Object v1;
    private final boolean isWeighted;
    /// O construtor da classe {@link Dijkstra}, que já executa o algoritmo
    /// na criação do objeto.
    /// @since 1.0
    ///
    /// @param graph Grafo que servirá de base para o algoritmo. Inalterável na classe.
    /// @param v1 Fonte que mapeará as distâncias. Inalterável na classe.
    public Dijkstra(final Graph<Object, Object> graph, final Object v1) {
        isWeighted = graph.isWeighted();
        if(!isWeighted) {
            System.out.println("não faz sentido usar dijkstra sem peso");
            return;
        }
        this.v1 = v1;
        Map<Object, Double> distances = new HashMap<>();
        PriorityQueue<Object> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Object vertex : graph.vertexSet()) {
            distances.put(vertex, Double.MAX_VALUE);
            predecessors.put(vertex, null);
        }

        distances.put(v1, 0.0);
        queue.add(v1);

        while (!queue.isEmpty()) {
            Object current = queue.poll();

            for (Object neighbor : graph.getNeightbours(current)) {
                try {
                    double edgeWeight = (Double) graph.getRelation(current, neighbor);
                    double newDist = distances.get(current) + edgeWeight;

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        predecessors.put(neighbor, current);
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                } catch (ClassCastException | NullPointerException e) {
                    return;
                }
            }
        }
    }
    /// O procedimento que retornará o caminho entre a fonte e um vértice v2.
    /// O funcionamento da montagem do caminho é simples:
    /// Criamos uma pilha. Começamos do vértice final.
    /// Em um ‘loop’ for, fazemos o passo ser o antecessor do vértice final
    /// e adicionamos esse vértice à pilha. Se chegarmos ao vértice, terminamos
    /// o ‘loop’.
    /// Se o primeiro elemento da pilha não for o vértice fonte, retornamos
    /// uma lista vazia. Caso contrário, retornamos o caminho final.
    /// @param v2 vértice de destino para o caminho
    ///
    /// @return menor caminho de v1 a v2
    /// @since 1.0
    public List<Object> constructSet(Object v2) {
        List<Object> path = new Stack<>();
        if(!isWeighted) {
            System.out.println("não faz sentido usar dijkstra sm peso");
            return path;
        }
        for (Object at = v2; at != null; at = predecessors.get(at)) {
            path.add(at);
            if (at.equals(v1)) break;
        }

        if (!path.getFirst().equals(v1)) {
            System.out.println("não deu pra achar um caminho");
            return List.of();
        }

        return path;
    }
}