package com.api.v1.people;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Document
@Getter
@NoArgsConstructor
public class Person {

    private UUID id = UUID.randomUUID();
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    private LocalDate birthDate;
    private AddressDto address;
    private String email;
    private String phoneNumber;
    private String createdAt = ZonedDateTime.now().toString();

    public static Person create(@Valid PersonRegistrationDto registrationDto) {
        Person person = new Person();
        person.firstName = registrationDto.firstName();
        person.middleName = registrationDto.middleName();
        person.lastName = registrationDto.lastName();
        person.ssn = registrationDto.ssn();
        person.birthDate = registrationDto.birthDate();
        person.address = registrationDto.address();
        person.email = registrationDto.email();
        person.phoneNumber = registrationDto.phoneNumber();
        return person;
    }
}
