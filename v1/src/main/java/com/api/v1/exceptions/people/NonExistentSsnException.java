package com.api.v1.exceptions.people;

public class NonExistentSsnException extends RuntimeException {
    public NonExistentSsnException() {
        super("The given SSN is not registered.");
    }
}
