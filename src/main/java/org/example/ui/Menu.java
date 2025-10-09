package org.example.ui;

import org.example.ui.commands.*;
import org.example.ui.commands.essentials.DegreeCommand;
import org.example.ui.commands.essentials.EdgesCommand;
import org.example.ui.commands.essentials.NeighboursCommand;
import org.example.ui.commands.essentials.RelationCommand;
import org.example.ui.commands.others.CommunityCommand;
import org.example.ui.commands.others.DijkstraCommand;

import java.io.IOException;
import java.util.*;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private static Menu instance;
    public static synchronized Menu getInstance() {
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }
    private Menu(){}
    public Scanner getScanner() {
        return sc;
    }
    public boolean menuAction() {
        Command command = selectCommand();
        try{
            command.execute();
        }catch(NullPointerException | IOException _){
            throw new RuntimeException("Fim do programa");
        }
        return true;
    }
    private Command selectCommand(){
        System.out.println("Menu");
        System.out.println("1: funcionalidades basicas");
        System.out.println("2: achar comunidades");
        System.out.println("3: melhor caminho entre um par");
        int option = sc.nextInt();
        return switch (option){
            case 1->basicFunctionalities();
            case 2->new CommunityCommand();
            case 3->new DijkstraCommand();
            default -> null;
        };
    }
    private Command basicFunctionalities() {
            System.out.println("\nfuncionalidades");
            System.out.println("1: verificar adjacência");
            System.out.println("2: valcular grau de um vértice");
            System.out.println("3: listar vizinhos de um vértice");
            System.out.println("4: listar todas as arestas");
            System.out.println("sair, caso contrário");

            int option = sc.nextInt();
            sc.nextLine();
            return switch (option) {
                case 1 -> new RelationCommand();
                case 2 -> new DegreeCommand();
                case 3 -> new NeighboursCommand();
                case 4 -> new EdgesCommand();
                default -> null;
            };
    }
}
