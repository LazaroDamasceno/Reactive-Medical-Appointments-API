package com.api.v1.people.domain;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import com.api.v1.people.dtos.PersonModificationDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Document
@Getter
@NoArgsConstructor
public class Person {

    private FullNameDto fullName;
    private LocalDate birthDate;
    private String ssn;
    private String email;
    private AddressDto address;
    private String phoneNumber;
    private String gender;
    private String createdAt;
    private String modifiedAt;

    private Person(PersonRegistrationDto registrationDto) {
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

    public void modify(PersonModificationDto modificationDto) {
        this.fullName = modificationDto.fullName();
        this.birthDate = modificationDto.birthDate();
        this.email = modificationDto.email();
        this.address = modificationDto.address();
        this.phoneNumber = modificationDto.phoneNumber();
        this.gender = modificationDto.gender();
        this.modifiedAt = ZonedDateTime.now().toString();
    }
}
