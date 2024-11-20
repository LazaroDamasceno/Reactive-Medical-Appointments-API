package com.api.v1.cards.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card was not found.");
    }
}
