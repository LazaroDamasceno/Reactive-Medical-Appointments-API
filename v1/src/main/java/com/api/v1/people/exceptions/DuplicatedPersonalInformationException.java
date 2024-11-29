package com.api.v1.people.exceptions;

public class DuplicatedPersonalInformationException extends RuntimeException {
    public DuplicatedPersonalInformationException() {
        super("The given email or SSN is already in use.");
    }
}
