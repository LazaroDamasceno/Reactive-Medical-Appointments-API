package com.api.v1.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.BsonDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Document
@Getter
@NoArgsConstructor
public class PersonAuditTrail {

    private Person person;
    private BsonDateTime createdAt;
    private ZoneOffset createdAtZone;

    private PersonAuditTrail(Person person) {
        this.person = person;
        this.createdAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        this.createdAtZone = ZoneOffset.UTC;
    }

    public static PersonAuditTrail create(Person person) {
        return new PersonAuditTrail(person);
    }
}
