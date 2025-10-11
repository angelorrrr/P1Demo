package org.example.ui.exceptions;

import java.util.Scanner;

public class InputException extends RuntimeException {
    public InputException(Scanner sc, String message) {
        sc.close();
        super("Wrong option selected.\n"+message);
    }
}
