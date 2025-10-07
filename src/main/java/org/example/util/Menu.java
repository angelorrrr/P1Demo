package org.example.util;

import org.example.algorythms.Dijkstra;
import org.example.algorythms.Kosaraju;
import org.example.graph.interfaces.Graph;

import java.util.*;

public class Menu {
    private final Graph<Object, Object> graph;
    private final Scanner sc = new Scanner(System.in);

    public Menu(Graph<?, ?> graph) {
        this.graph = (Graph<Object, Object>) graph;
        for(boolean action = true; action;){
            action = showOptions();
            System.out.println("continuando");
        }
        System.out.println("fim");
    }

    private boolean showOptions(){
        System.out.println("Menu");
        System.out.println("1: funcionalidades basicas");
        System.out.println("2: achar comunidades");
        System.out.println("3: melhor caminho entre um par");
        int option = sc.nextInt();
        switch (option){
            case 1->basicFunctionalities();
            case 2->findCommunity();
            case 3->dijkstra();
            default -> {
                return false;
            }
        }
        return true;
    }
    private void dijkstra(){
        System.out.println("Escolha a fonte");
        Optional<Object> v1 = selecionarVertice("fonte");
        if(v1.isEmpty()){
            System.out.println("invalido");
            return;
        }
        Dijkstra dijkstra = new Dijkstra(graph, v1.get());
        Optional<Object> target = selecionarVertice("alvo");
        if(target.isEmpty()){
            System.out.println("invalido");
            return;
        }
        System.out.println(dijkstra.constructSet(target.get()));
    }
    private void findCommunity(){
        Kosaraju<?, ?> solution = new Kosaraju<>(graph);
        solution.showCommunities();
    }
    private void basicFunctionalities() {

            System.out.println("\nfuncionalidades");
            System.out.println("1: verificar adjacência");
            System.out.println("2: valcular grau de um vértice");
            System.out.println("3: listar vizinhos de um vértice");
            System.out.println("4: listar todas as arestas");
            System.out.println("sair, caso contrário");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> seekRelation();
                case 2 -> degree();
                case 3 -> listNeighbours();
                case 4 -> listEdges();
                default -> {
                }
            }
    }

    private void seekRelation() {
        System.out.println("selecione os dois vértices:");
        Optional<Object> v1 = selecionarVertice("primeiro");
        Optional<Object> v2 = selecionarVertice("segundo");
        if(v1.isEmpty() || v2.isEmpty())
            return;
        boolean adj = graph.hasRelation(v1.get(), v2.get());
        System.out.printf("%s e %s %ssão adjacentes.\n", v1.get(), v2.get(), adj ? "" : "não ");
    }

    private void degree() {
        Optional<Object> v = selecionarVertice("escolha o vértice:");
        if(v.isEmpty()){
            return;
        }

        int grau = graph.degreeOf(v.get());
        System.out.printf("d(%s): %d\n", v.get(), grau);
    }

    private void listNeighbours() {
        Optional<Object> v = selecionarVertice("escolha o vértice:");
        if(v.isEmpty()){
            return;
        }
        Object v1 = v.get();
        Collection<Object> vizinhos = graph.getNeightbours(v1);
        System.out.printf("vizinhos de %s: %s\n", v1, vizinhos);
    }

    private void listEdges() {
        System.out.println("arestas do grafo:");
        for (Object v1 : graph.vertexSet()) {
            for (Object v2 : graph.getNeightbours(v1)) {
                if (!graph.isDirected() && v1.toString().compareTo(v2.toString()) > 0) continue;
                Object rel = graph.getRelation(v1, v2);
                System.out.printf("%s --(%s)--> %s\n", v1, rel != null ? rel : "", v2);
            }
        }
    }

    private Optional<Object> selecionarVertice(String mensagem) {
        List<Object> vertices = new ArrayList<>(graph.vertexSet());
            System.out.println("\n" + mensagem);
            for (int i = 0; i < vertices.size(); i++) {
                System.out.printf("%d: %s\n", i + 1, vertices.get(i));
            }
            System.out.print("escolha o vértice: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao >= 1 && opcao <= vertices.size()) {
                return Optional.of(vertices.get(opcao - 1));
            } else {
                System.out.println("inválido.");
            }
        return Optional.empty();
    }
}
