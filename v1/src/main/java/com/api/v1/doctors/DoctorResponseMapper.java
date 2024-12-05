package com.api.v1.doctors;

import com.api.v1.people.PersonResponseMapper;

public final class DoctorResponseMapper {

    public static DoctorResponseDto map(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getLicenseNumber(),
                PersonResponseMapper.map(doctor.getPerson()),
                doctor.getHiredAt(),
                doctor.getHiredAtZone(),
                doctor.getTerminatedAt(),
                doctor.getTerminatedAtZone()
        );
    }

}
