package org.example;

import org.example.algorithms.DFS;
import org.example.algorithms.Kosaraju;
import org.example.core.interfaces.Graph;
import org.example.ui.Context;
import org.example.ui.Menu;
import org.example.io.TXTReader;

public class Main {
    public static final String filePath = "graph.txt";

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");
        System.setProperty("java.awt.headless", "true");
        Menu menu = Menu.getInstance();
        for(;;){
            menu.executeSelectedCommand();                                                                                                                                                                                                                     ;
            System.out.println("continuando");
        }
    }
}