package com.api.v1.doctors.domain;

import com.api.v1.doctors.dtos.DoctorLicenseNumberDto;
import com.api.v1.people.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
@Getter
@NoArgsConstructor
public class Doctor {

    @BsonId
    private ObjectId id;
    private DoctorLicenseNumberDto licenseNumber;
    @Setter
    private Person person;
    private String createdAt;
    private String hiredAt;
    private String terminatedAt;

    private Doctor(DoctorLicenseNumberDto licenseNumber, Person person) {
        this.id = new ObjectId();
        this.licenseNumber = licenseNumber;
        this.person = person;
        this.createdAt = ZonedDateTime.now().toString();
        this.hiredAt = ZonedDateTime.now().toString();
    }

    public static Doctor create(DoctorLicenseNumberDto licenseNumber, Person person) {
        return new Doctor(licenseNumber, person);
    }

    public void terminate() {
        this.terminatedAt = ZonedDateTime.now().toString();
    }
}
