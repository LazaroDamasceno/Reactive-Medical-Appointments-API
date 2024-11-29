package com.api.v1.cards.domain;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

import java.time.*;

public record Card(
        String type,
        ObjectId number,
        LocalDate dueDate,
        String cvc,
        String ownerName,
        String OwnerSsn,
        BsonDateTime createAt,
        ZoneOffset createdAtZone
) {

    public static Card create(
            String type,
            LocalDate dueDate,
            String cvc,
            String ownerName,
            String OwnerSsn
    ) {
        return new Card(
                type,
                new ObjectId(),
                dueDate,
                cvc,
                ownerName,
                OwnerSsn,
                new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()),
                ZoneOffset.UTC
        );
    }

}
