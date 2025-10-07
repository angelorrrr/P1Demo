package org.example.entity;

public class Client{
    private static int numClients;
    private final String name;
    private final int id;

    public Client(String name) {
        this.name = name;
        id = numClients++;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client other)) return false;
        return id == other.id;
    }
}
