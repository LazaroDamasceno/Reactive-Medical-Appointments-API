package com.api.v1.domain.people;

import com.api.v1.dtos.people.PersonAddressDto;
import com.api.v1.dtos.people.PersonModificationDto;
import com.api.v1.dtos.people.PersonRegistrationDto;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time. *;

@Document
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
    private String createdAt;
    private ZoneId createdAtZone;
    private String modifiedAt;
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
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public Person() {
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
        this.modifiedAt = LocalDateTime.now().toString();
        this.modifiedAtZone = ZoneOffset.UTC;
    }

    public String getFullName() {
        if (middleName.isEmpty()) return "%s %s".formatted(firstName, lastName);
        return "%s %s %s".formatted(firstName, middleName, lastName);
    }

    public ObjectId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmail() {
        return email;
    }

    public PersonAddressDto getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ZoneId getCreatedAtZone() {
        return createdAtZone;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public ZoneId getModifiedAtZone() {
        return modifiedAtZone;
    }
}
