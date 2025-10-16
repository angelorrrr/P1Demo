package org.example.core.interfaces;

import java.util.Set;

///
/// ‘Interface’ que abstrai os grafos no projeto.
/// Essa classe pode gerar grafos com vértices e arestas genéricos,
/// Você pode usá-la em qualquer panorama: seja para representar usuários em uma
/// rede, inteiros, qualquer tipo derivado.
///
/// Cada vértice pode estar associado a outro em qualquer valor, graças ao tipo genérico.
///
/// Suponha que você criou uma classe Client.java e você quer representar clientes asssociados
/// por Strings em um grafo.
/// Você procederia assim:
/// <pre><code>
/// declare o grafo com base nos tipos dos vértices e nas arestas
/// //o primeiro tipo para os vértices, o segundo para as arestas.
/// Graph<Client, String> graph;
/// //escolha uma implementação (a exemplo> lista de adjacencias)
/// graph = new AdjacencyListGraph<>(true, true);
/// </code></pre>
/// Após criar o grafo, você pode usar qualquer um dos métodos declarados
/// nessa interface para extrair informações, ou ainda utilizar alguns dos nossos
/// algoritmos para resolver problemas.
///
/// todo: se voce quiser usar uma classe personalizada, sobreponha o método equals para que as operações de busca deem certo (se não, maior parte do que está aqui não vai funcionar)
/// @param <U> tipo da aresta
/// @param <V> tipo do vértice
public interface Graph<V, U> {
    //basic functionalities
    /// método que adiciona um vértice ao grafo
    /// @param vertex vértice a ser adicionado no grafo
    void addVertex(V vertex);
    /// método que remove um vértice do grafo
    /// @param vertex vértice a ser removido do grafo
    void removeVertex(V vertex);
    ///  método que adiciona uma aresta ao grafo entre dois vértices existentes.
    ///
    /// Esse método deve considerar digrafos nas implementações.
    /// @param first vertice de origem
    /// @param second vertice de destino
    /// @param weight valor do peso
    void addRelation(V first, V second, U weight);
    /// método que remove uma aresta entre dois vértices existentes.
    ///
    /// a ordem importa para digrafos
    /// @param first o vértice de origem
    /// @param second o vértice de destino
    void removeRelation(V first, V second);
    /// método que retorna a aresta entre dois vértices
    ///
    /// a ordem importa para digrafos
    /// @param first o vértice de origem
    /// @param second o vértice de destino
    ///
    /// @return a aresta entre os dois
    U getRelation(V first, V second);
    //algorithm functionalities
    /// método que verifica a existência de uma aresta entre dois vértices
    ///
    /// a ordem importa para digrafos
    /// @param first o vértice de origem
    /// @param second o vértice de destino
    ///
    /// @return true caso exista, false caso contrário
    boolean hasRelation(V first, V second);
    /// método que retorna o grau de um vértice existente
    ///
    /// @param vertex vértice a ser analisado
    ///
    /// @return o número inteiro de conexões
    int degreeOf(V vertex);
    /// método que verifica a existência de um vértice no grafo
    ///
    /// @param vertex vértice a ser analisado
    ///
    /// @return verdadeiro caso exista, falso caso contrário
    boolean hasVertex(V vertex);
    /// método que retorna o conjunto de vizinhos no grafo a partir de um vértice existente
    /// @param vertex vértice que terá os vizinhos verificados
    ///
    /// @return um conjunto de vértices vizinhos
    Set<V> getNeightbours(V vertex);
    /// método que retorna todos os vértices no grafo
    ///
    /// @return todos os vértices do grafo
    Set<V> vertexSet();
    /// método que transpõe o grafo
    ///
    /// @return outro grafo g' com as arestas invertidas
    Graph<V, U> getTransposed();
    //general atributes
    /// método que retorna um grafo com base na classe que o implementa.
    /// Útil para o métdo {@link Graph#getTransposed()}
    ///
    /// @return um grafo do mesmo tipo de sua implementação
    Graph<V, U> constructGraph();
    /// método que retorna o número de vértices do grafo
    ///
    /// @return o número de vértices do grafo
    int size();
    /// método que printa os vértices no console
    void showVertex();
    ///  método que printa as arestas no console
    void showRelations();
    /// método que verifica se um grafo é dígrafo ou não
    ///
    /// @return true se for digrafo, false caso contrário
    boolean isDirected();
    /// método que verifica se um grafo tem algum vértice
    ///
    /// @return true se não tiver nenhum vértice, false se não
    boolean isEmpty();
    /// método que verifica se um grafo tem peso ou não
    ///
    /// @return true se tem pesos, false se não
    boolean isWeighted();
    /// enum que representa um tipo de grafo.
    /// esse enum pode assumir dois valores: DIRECTED ou UNDIRECTED
    /// para formatar os valores para as entradas no arquivo de texto,
    /// criamos um atributo name, que é igual ao valor no arquivo de texto.
    ///
    /// esse enum é muito bom para a legibilidade do programa
    enum GraphType {
        /// atributo que representa grafos direcionados
        DIRECTED("D"),
        /// atributo que representa grafos não direcionados
        UNDIRECTED("ND");
        /// nome do tipo na formatação do problema
        final String name;
        /// construtor de GraphType
        /// @param name o nome formatado para o arquivo de leitura graph.txt
        GraphType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
        /// método que verifica dentro das constantes criadas uma equivalência entre o parâmetro
        /// texto e o name dos objetos. Isso ignora maiúsculas e minúsculas.
        ///
        /// Se não existir equivalência, encerra o programa
        /// @param text texto a ser verificado
        /// @return o tipo do grafo a partir da string.
        ///
        ///
        public static GraphType fromValue(String text) {
            for (GraphType type : GraphType.values()) {
                if (type.name.equalsIgnoreCase(text)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Valor de tipo de grafo inválido: " + text);
        }
    }
}