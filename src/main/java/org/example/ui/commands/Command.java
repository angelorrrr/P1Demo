package org.example.ui.commands;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public interface Command {
    List<String> history = new LinkedList<>();
    void execute();
    String description();
    default void appendHistory(String answer){
        history.add(description());
        history.add(answer);
    }

    static Command showHistory() {
        return new Command() {
            @Override
            public void execute() {
                history.forEach(System.out::println);
            }

            @Override
            public String description() {
                return "";
            }
        };
    }
}
