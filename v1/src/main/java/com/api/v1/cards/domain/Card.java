package com.api.v1.cards.domain;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

import java.time.*;

public record Card(
        ObjectId id,
        String type,
        ObjectId number,
        LocalDate dueDate,
        String cvc,
        String ownerName,
        String OwnerSsn,
        String createAt,
        ZoneId createdAtZone
) {

    public static Card create(
            String type,
            LocalDate dueDate,
            String cvc,
            String ownerName,
            String OwnerSsn
    ) {
        return new Card(
                new ObjectId(),
                type,
                new ObjectId(),
                dueDate,
                cvc,
                ownerName,
                OwnerSsn,
                LocalDateTime.now().toString(),
                ZoneId.systemDefault()
        );
    }

}
