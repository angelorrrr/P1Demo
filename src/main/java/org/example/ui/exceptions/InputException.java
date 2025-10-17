package org.example.ui.exceptions;

import java.util.Scanner;

public class InputException extends RuntimeException {
    public InputException(Scanner sc, String message) {
        super("Wrong option selected.\n"+message);
        sc.close();
    }
}
