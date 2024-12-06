package com.api.v1.exceptions.people;

public class DuplicatedSsnException extends RuntimeException {
    public DuplicatedSsnException() {
        super("The given SSN is already in use.");
    }
}
