import org.example.algorythms.Kosaraju;
import org.example.graph.interfaces.Graph;
import org.example.util.GraphView;
import org.example.util.Menu;
import org.example.util.TXTReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws IOException {
    System.setProperty("org.graphstream.ui", "swing");
    System.setProperty("java.awt.headless", "true");
    TXTReader txtReader = new TXTReader("graph.txt");
    TXTReader weightedReader = new TXTReader("weighted.txt");
    Graph<?, ?> weighted = weightedReader.getWeightedGraph();
    Graph<?, ?> graph = txtReader.getGraph();
    new Menu(graph);
    new Menu(weighted);
}