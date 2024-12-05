package com.api.v1.doctors;

import com.api.v1.people.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public static Doctor create(String licenseNumber, Person person) {
        return new Doctor(licenseNumber, person);
    }

    public void terminate() {
        this.terminatedAt = LocalDateTime.now().toString();
        this.terminatedAtZone = ZoneId.systemDefault();
    }
}
