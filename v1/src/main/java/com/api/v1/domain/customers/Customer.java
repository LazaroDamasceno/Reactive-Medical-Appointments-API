package com.api.v1.domain.customers;

import com.api.v1.domain.people.Person;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.*;

@Document
public class Customer {

    @BsonId
    private ObjectId id;
    private Person person;
    private String createdAt;
    private ZoneId createdAtZone;

    private Customer(Person person) {
        this.id = new ObjectId();
        this.person = person;
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public Customer() {
    }

    public static Customer create(Person person) {
        return new Customer(person);
    }

    public ObjectId getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ZoneId getCreatedAtZone() {
        return createdAtZone;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
