package com.api.v1.people.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
@Getter
@NoArgsConstructor
public class PersonAuditTrail {

    private Person person;
    private final String createdAt = ZonedDateTime.now().toString();

    private PersonAuditTrail(Person person) {
        this.person = person;
    }

    public static PersonAuditTrail create(Person person) {
        return new PersonAuditTrail(person);
    }
}
