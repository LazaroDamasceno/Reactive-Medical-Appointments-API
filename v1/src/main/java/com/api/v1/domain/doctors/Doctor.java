package com.api.v1.domain.doctors;

import com.api.v1.domain.people.Person;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

@Document
public class Doctor {

    @BsonId
    private ObjectId id;
    private String licenseNumber;
    private Person person;
    private String createdAt;
    private ZoneId createdAtZone;
    private String hiredAt;
    private ZoneId hiredAtZone;
    private String terminatedAt;
    private ZoneId terminatedAtZone;

    private Doctor(String licenseNumber, Person person) {
        this.id = new ObjectId();
        this.licenseNumber = licenseNumber;
        this.person = person;
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
        this.hiredAt = LocalDateTime.now().toString();
        this.hiredAtZone = ZoneId.systemDefault();
    }

    public Doctor() {
    }

    public static Doctor create(String licenseNumber, Person person) {
        return new Doctor(licenseNumber, person);
    }

    public void markAsTerminated() {
        this.terminatedAt = LocalDateTime.now().toString();
        this.terminatedAtZone = ZoneId.systemDefault();
    }

    public void markAsRehired() {
        this.terminatedAt = null;
        this.terminatedAtZone = null;
    }

    public ObjectId getId() {
        return id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
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

    public String getHiredAt() {
        return hiredAt;
    }

    public ZoneId getHiredAtZone() {
        return hiredAtZone;
    }

    public String getTerminatedAt() {
        return terminatedAt;
    }

    public ZoneId getTerminatedAtZone() {
        return terminatedAtZone;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
