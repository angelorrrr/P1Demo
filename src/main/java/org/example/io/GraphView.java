package org.example.io;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.images.Resolutions;
import org.graphstream.ui.swing.util.SwingFileSinkImages;

import java.io.IOException;
//a melhorar
public class GraphView {
    public static <T, U> String processGraph(org.example.core.interfaces.Graph<T, U> customGraph) {
        Graph graph = new SingleGraph("grafo");

        for (T v : customGraph.vertexSet()) {
            graph.addNode(v.toString()).setAttribute("label", v.toString());
        }

        for (T v1 : customGraph.vertexSet()) {
            for (T v2 : customGraph.getNeightbours(v1)) {
                if (!customGraph.isDirected() && v1.toString().compareTo(v2.toString()) > 0) continue;
                String id = v1 + "-" + v2;
                if (graph.getEdge(id) == null)
                    graph.addEdge(id, v1.toString(), v2.toString(), customGraph.isDirected())
                            .setAttribute("ui.label", String.valueOf(customGraph.getRelation(v1, v2)));
            }
        }
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
