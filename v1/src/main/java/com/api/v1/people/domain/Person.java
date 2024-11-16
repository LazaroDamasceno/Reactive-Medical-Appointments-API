package com.api.v1.people.domain;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Document
@Getter
public class Person {

    @Id
    private final UUID id;
    private FullNameDto fullName;
    private LocalDate birthDate;
    private final String ssn;
    private String email;
    private AddressDto address;
    private String phoneNumber;
    private String gender;
    private final String createdAt;

    private Person(PersonRegistrationDto registrationDto) {
        this.id = UUID.randomUUID();
        this.fullName = registrationDto.fullName();
        this.birthDate = registrationDto.birthDate();
        this.ssn = registrationDto.ssn();
        this.email = registrationDto.email();
        this.address = registrationDto.address();
        this.phoneNumber = registrationDto.phoneNumber();
        this.gender = registrationDto.gender();
        this.createdAt = ZonedDateTime.now().toString();
    }

    public static Person create(PersonRegistrationDto registrationDto) {
        return new Person(registrationDto);
    }
}
