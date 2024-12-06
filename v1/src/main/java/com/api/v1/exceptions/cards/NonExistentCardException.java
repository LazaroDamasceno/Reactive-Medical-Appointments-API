package com.api.v1.exceptions.cards;

public class NonExistentCardException extends RuntimeException {
    public NonExistentCardException() {
        super("Th sought card was not found.");
    }
}
