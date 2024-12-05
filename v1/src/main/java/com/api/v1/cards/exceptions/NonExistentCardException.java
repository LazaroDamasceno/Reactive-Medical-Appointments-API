package com.api.v1.cards.exceptions;

public class NonExistentCardException extends RuntimeException {
    public NonExistentCardException() {
        super("Th sought card was not found.");
    }
}
