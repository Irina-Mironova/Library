package ru.Irina.Library.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {
    private List<String> messages;

    public ValidationException(List<String> messages) {
        super(messages.stream().collect(Collectors.joining(System.lineSeparator())));
    }
}

