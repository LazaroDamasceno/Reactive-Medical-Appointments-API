package com.api.v1.cards.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
@Getter
@NoArgsConstructor
public class Card {

    private String type;
    private final ObjectId number = new ObjectId();
    private final String createdAt = ZonedDateTime.now().toString();

    private Card(String type) {
        this.type = type;
    }

    public static Card create(String type) {
        return new Card(type);
    }
}
