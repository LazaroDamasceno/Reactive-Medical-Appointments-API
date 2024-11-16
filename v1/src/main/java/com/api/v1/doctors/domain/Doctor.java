package com.api.v1.doctors.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class Doctor {

    private String personId;
    private String licenseNumber;
    private String speciality;
    private String createdAt;
    private String hiredAt;
    private String terminatedAt;

    public Doctor(String personId, String licenseNumber, String speciality) {
        this.personId = personId;
        this.licenseNumber = licenseNumber;
        this.speciality = speciality;
        this.createdAt = ZonedDateTime.now().toString();
    }

    public static Doctor create(String personId, String licenseNumber, String speciality) {
        return new Doctor(personId, licenseNumber, speciality);
    }

}
