package org.example.algorithms;

import org.example.core.interfaces.Graph;

import java.util.*;
import java.util.function.Consumer;
///
/// <b>Algoritmo de {@link Kosaraju} para SCC</b>
///
/// Objetivo: receber um {@link Graph} de qualquer natureza e
/// retornar um conjunto de componentes conexos tageados por {@link String}.
///
/// O algoritmo que resolve o problema está representado abaixo
/// <code>
/// <pre>
/// funcao Kosaraju(Grafo g):
///     Pilha p
///     para cada vertice v em g
///         dfs(g, v, v≥adicione v a p)
///     g' = g transposto
///     enquanto(p é não vazio):
///         v = ultimo vertice de p
///         Pilha pilhaConexos
///         dfs(g', v, v>visite v, adicione v a pilhaConexos)
///         associe pilhaConexos á iésima comunidade
/// </pre></code>
///
/// Após a criação do objeto de {@link Kosaraju}, devemos ter um dicionário
/// que aponta para os componentes conexos pelos nomes associados
/// na ordem de verificação.
///
/// Aviso: desencorajar a herança de dicionários, encontrar uma forma
/// menos complicada de repesentar essas assoçiações.
///
/// Para a posteridade: gerar uma lista de {@link Graph} que são componentes conexos
public class Kosaraju<V, U> extends HashMap<String, List<V>> {

    /// Construtor da classe {@link Kosaraju}
    ///
    /// @param graph o grafo a gerar os componentes conexos
    public Kosaraju(Graph<V, U> graph){
        Stack<V> stack = new Stack<>();
        DFS.search(graph, stack::push, v->{});


        Graph<V, U> transposed = graph.getTransposed();
        Set<V> visited = new HashSet<>();

        int counter = 1;
        while (!stack.empty()) {
            V vertex = stack.pop();
            if(visited.contains(vertex)) continue;

            List<V> list = new LinkedList<>();
            Consumer<V> preOrderAction = (v)->{
                visited.add(v);
                list.add(v);
            };
            DFS.dfs(vertex, visited, transposed, preOrderAction, (v)->{});

            put("comunidade "+counter+": ", list);
            counter++;
        }

    }
    /// procedimento que rapidamente exibe as comunidades
    public void showCommunities(){
        System.out.println(this);
    }

    /// Sobreposição do procedimento herdado toString para exibir as comunidades.
    /// @return a {@link String} com cada componente conexo e sua chave
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (java.util.Map.Entry<String, List<V>> entry : entrySet()) {
            sb.append(entry.getKey()).append(": ");
            sb.append(entry.getValue().toString()).append("\n");

        }
        return sb.toString();
    }
}