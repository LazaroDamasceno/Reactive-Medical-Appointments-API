package com.api.v1.people.domain;

import com.api.v1.people.dtos.PersonAddressDto;
import com.api.v1.people.dtos.PersonModificationDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Document
@Getter
@NoArgsConstructor
public class Person {

    @BsonId
    private ObjectId id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String ssn;
    private String email;
    private PersonAddressDto address;
    private String phoneNumber;
    private String gender;
    private Date createdAt;
    private ZoneId createdAtZone;
    private Date modifiedAt;
    private ZoneId modifiedAtZone;

    private Person(PersonRegistrationDto registrationDto) {
        this.id = new ObjectId();
        this.firstName = registrationDto.firstName();
        this.middleName = registrationDto.middleName();
        this.lastName = registrationDto.lastName();
        this.birthDate = registrationDto.birthDate();
        this.ssn = registrationDto.ssn();
        this.email = registrationDto.email();
        this.address = registrationDto.address();
        this.phoneNumber = registrationDto.phoneNumber();
        this.gender = registrationDto.gender();
        this.createdAt = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static Person create(PersonRegistrationDto registrationDto) {
        return new Person(registrationDto);
    }

    public void modify(PersonModificationDto modificationDto) {
        this.firstName = modificationDto.firstName();
        this.middleName = modificationDto.middleName();
        this.lastName = modificationDto.lastName();
        this.birthDate = modificationDto.birthDate();
        this.email = modificationDto.email();
        this.address = modificationDto.address();
        this.phoneNumber = modificationDto.phoneNumber();
        this.gender = modificationDto.gender();
        this.modifiedAt = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        this.modifiedAtZone = ZoneId.systemDefault();
    }

    public String getFullName() {
        if (middleName.isEmpty()) return "%s %s".formatted(firstName, lastName);
        return "%s %s %s".formatted(firstName, middleName, lastName);
    }
}
