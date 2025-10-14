package org.example.io;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.images.Resolutions;
import org.graphstream.ui.swing.util.SwingFileSinkImages;

import java.io.IOException;
/**
 * Classe utilitária responsável por gerar a visualização gráfica de um grafo.
 * <p>
 * Utiliza a biblioteca externa GraphStream para renderizar a estrutura do grafo
 * (vértices e arestas) e exportá-la para um arquivo de imagem no formato PNG.
 *
 * @author Angelo - Implementação
 * @author Camila - Documentação e Revisão
 */
public class GraphView {
    /**
     * Processa um grafo da estrutura interna do projeto e o converte em uma imagem PNG.
     *
     * @param customGraph O grafo do nosso projeto que será visualizado.
     * @param <T> O tipo dos vértices do grafo (ex: String, Integer).
     * @param <U> O tipo dos pesos das arestas do grafo (ex: Double).
     * @return Uma mensagem de texto indicando o sucesso ou falha da operação de salvamento.
     */
    public static <T, U> String processGraph(org.example.core.interfaces.Graph<T, U> customGraph) {
        // 1. Cria um grafo da biblioteca GraphStream para a visualização
        Graph graph = new SingleGraph("grafo");

        // 2. Adiciona todos os vértices do nosso grafo como "nós" no grafo visual
        for (T v : customGraph.vertexSet()) {
            graph.addNode(v.toString()).setAttribute("label", v.toString());
        }

        // 3. Adiciona todas as arestas do nosso grafo como "arestas" no grafo visual
        for (T v1 : customGraph.vertexSet()) {
            for (T v2 : customGraph.getNeightbours(v1)) {
                // Truque para não desenhar a mesma aresta duas vezes em grafos não-dirigidos
                if (!customGraph.isDirected() && v1.toString().compareTo(v2.toString()) > 0) continue;
                String id = v1 + "-" + v2;
                if (graph.getEdge(id) == null)
                    graph.addEdge(id, v1.toString(), v2.toString(), customGraph.isDirected())
                            .setAttribute("ui.label", String.valueOf(customGraph.getRelation(v1, v2)));
            }
        }

        // 4. Define o estilo visual do grafo (cores, tamanhos de texto, etc.)
        graph.setAttribute("ui.stylesheet", """
            node {
                text-color: blue;
                text-size: 14px;
                fill-color: lightgray;
            }
            edge {
                text-color: blue;
                text-size: 12px;
                fill-color: gray;
            }
        """);
        // 5. Salva o grafo renderizado como um arquivo de imagem PNG
        try {
            String outputFile = "grafo.png";
            var fsi = new SwingFileSinkImages(FileSinkImages.OutputType.PNG, Resolutions.HD720);
            fsi.setLayoutPolicy(FileSinkImages.LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
            fsi.writeAll(graph, outputFile);
            System.out.println("Imagem gerada: "+outputFile);
            return "salvo em "+outputFile;
        } catch (IOException e) {
            System.err.println("Erro ao exportar: " + e.getMessage());
            return "falha ao salvar";
        }
    }
}
