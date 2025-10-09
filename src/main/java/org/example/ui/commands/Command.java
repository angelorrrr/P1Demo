package org.example.ui.commands;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface Command {
    List<String> history = new LinkedList<>();
    void execute(String... args) throws IOException;
    String description();
    default void appendHistory(String answer){
        history.add(description());
        history.add(answer);
    }
}
