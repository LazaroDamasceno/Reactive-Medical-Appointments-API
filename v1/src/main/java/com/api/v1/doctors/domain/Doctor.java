package com.api.v1.doctors.domain;

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
public class Doctor {

    @BsonId
    private ObjectId id;
    private String licenseNumber;
    @Setter
    private Person person;
    private BsonDateTime createdAt;
    private ZoneId createdAtZone;
    private BsonDateTime hiredAt;
    private ZoneId hiredAtZone;
    private BsonDateTime terminatedAt;
    private ZoneId terminatedAtZone;

    private Doctor(String licenseNumber, Person person) {
        this.id = new ObjectId();
        this.licenseNumber = licenseNumber;
        this.person = person;
        this.createdAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        this.createdAtZone = ZoneId.systemDefault();
        this.hiredAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        this.hiredAtZone = ZoneId.systemDefault();
    }

    public static Doctor create(String licenseNumber, Person person) {
        return new Doctor(licenseNumber, person);
    }

    public void terminate() {
        this.terminatedAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        this.terminatedAtZone = ZoneId.systemDefault();
    }
}
