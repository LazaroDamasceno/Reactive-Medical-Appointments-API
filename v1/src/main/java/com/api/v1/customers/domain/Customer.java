package com.api.v1.customers.domain;

import com.api.v1.people.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.BsonDateTime;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.*;

@Document
@Getter
@NoArgsConstructor
public class Customer {

    @BsonId
    private ObjectId id;
    @Setter
    private Person person;
    private String createdAt;
    private ZoneId createdAtZone;

    private Customer(Person person) {
        this.id = new ObjectId();
        this.person = person;
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static Customer create(Person person) {
        return new Customer(person);
    }

}
