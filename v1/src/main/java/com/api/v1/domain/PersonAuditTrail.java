package com.api.v1.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document
public record PersonAuditTrail(
        ObjectId id,
        Person person,
        String createdAt,
        ZoneId createdAtZone
) {


    public static PersonAuditTrail create(Person person) {
        return new PersonAuditTrail(
                new ObjectId(),
                person,
                LocalDateTime.now().toString(),
                ZoneId.systemDefault()
        );
    }
}
