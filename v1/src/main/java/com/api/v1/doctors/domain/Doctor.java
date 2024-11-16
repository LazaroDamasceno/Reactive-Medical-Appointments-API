package com.api.v1.doctors.domain;

import java.time.ZonedDateTime;

public record Doctor(
        String personId,
        String licenseNumber,
        String specialty,
        String createdAt
) {

    public static Doctor create(
            String personId,
            String licenseNumber,
            String specialty
    ) {
        return new Doctor(
                personId,
                licenseNumber,
                specialty,
                ZonedDateTime.now().toString()
        );
    }

}
