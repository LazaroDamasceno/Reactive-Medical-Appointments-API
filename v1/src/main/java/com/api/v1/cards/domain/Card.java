package com.api.v1.cards.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

@Document
public record Card(
        @BsonId
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
