package com.api.v1.people.internal.exceptions;

public class DuplicatedSsnException extends RuntimeException {
    public DuplicatedSsnException() {
        super("The given SSN is already in use.");
    }
}
