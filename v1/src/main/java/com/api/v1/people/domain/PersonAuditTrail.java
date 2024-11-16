package com.api.v1.people.domain;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Getter
@NoArgsConstructor
public class PersonAuditTrail {

    private FullNameDto fullName;
    private LocalDate birthDate;
    private String ssn;
    private String email;
    private AddressDto address;
    private String phoneNumber;
    private String gender;
    private String createdAt;
    private String modifiedAt;

    private PersonAuditTrail(Person person) {
        this.fullName = person.getFullName();
        this.birthDate = person.getBirthDate();
        this.ssn = person.getSsn();
        this.email = person.getEmail();
        this.address = person.getAddress();
        this.phoneNumber = person.getPhoneNumber();
        this.gender = person.getGender();
        this.createdAt = person.getCreatedAt();
        this.modifiedAt = person.getModifiedAt();
    }

    public static PersonAuditTrail create(Person person) {
        return new PersonAuditTrail(person);
    }
}
