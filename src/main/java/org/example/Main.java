import org.example.core.interfaces.Graph;
import org.example.ui.Menu;
import org.example.io.TXTReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws IOException {

    System.setProperty("org.graphstream.ui", "swing");
    System.setProperty("java.awt.headless", "true");
    Menu menu = Menu.getInstance();
    for(boolean action = true; action;){
        action = menu.menuAction();                                                                                                                                                                                                                     ;
        System.out.println("continuando");
    }
    System.out.println("fim");
}