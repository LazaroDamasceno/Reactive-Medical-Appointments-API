package com.api.v1.exceptions.people;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException() {
        super("The given email is already in use.");
    }
}
