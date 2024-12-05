package com.api.v1.people.internal.exceptions;

public class NonExistentSsnException extends RuntimeException {
    public NonExistentSsnException() {
        super("The given SSN is not registered.");
    }
}
