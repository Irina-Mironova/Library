package ru.Irina.Library.exceptions;

public class AmountNotEnoughException extends RuntimeException {
    public AmountNotEnoughException(String message) {
        super(message);
    }
}
