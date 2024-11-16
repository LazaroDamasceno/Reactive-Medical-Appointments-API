package com.api.v1.doctors.domain;

import java.time.ZonedDateTime;

public record Doctor(
        String personId,
        String licenseNumber,
        String speciality,
        String createdAt
) {

    public static Doctor create(
            String personId,
            String licenseNumber,
            String speciality
    ) {
        return new Doctor(
                personId,
                licenseNumber,
                speciality,
                ZonedDateTime.now().toString()
        );
    }

}
