package ru.Irina.Library.exceptions;

public class BookMissingException extends RuntimeException {
    public BookMissingException(String message) {
        super(message);
    }
}

