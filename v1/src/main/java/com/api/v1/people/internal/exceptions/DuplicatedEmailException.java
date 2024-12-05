package com.api.v1.people.internal.exceptions;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException() {
        super("The given email is already in use.");
    }
}
