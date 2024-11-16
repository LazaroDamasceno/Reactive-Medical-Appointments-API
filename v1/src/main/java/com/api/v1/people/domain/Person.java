package com.api.v1.people.domain;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Person {

    private FullNameDto fullName;
    private String ssn;
    private Date birthDate;
    private AddressDto address;
    private String email;
    private String phoneNumber;
    private String customerId;
    private String doctorId;
    private final String createdAt = ZonedDateTime.now().toString();

    private Person(@Valid PersonRegistrationDto registrationDto) {
        fullName = registrationDto.fullName();
        ssn = registrationDto.ssn();
        birthDate = registrationDto.birthDate();
        address = registrationDto.address();
        email = registrationDto.email();
        phoneNumber = registrationDto.phoneNumber();
    }

    public static Person create(@Valid PersonRegistrationDto registrationDto) {
        return new Person(registrationDto);
    }
}
