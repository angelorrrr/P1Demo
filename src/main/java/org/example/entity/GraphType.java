package org.example.entity;

public enum GraphType {
    DIRECTED("D"),
    UNDIRECTED("ND");
    final String name;
    GraphType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    public static GraphType fromValue(String text) {
        for (GraphType type : GraphType.values()) {
            if (type.name.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Valor de tipo de grafo inválido: " + text);
    }
}
