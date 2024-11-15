package com.api.v1.people.domain;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class Person {

    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    private LocalDate birthDate;
    private AddressDto address;
    private String email;
    private String phoneNumber;
    private final String createdAt = ZonedDateTime.now().toString();

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
