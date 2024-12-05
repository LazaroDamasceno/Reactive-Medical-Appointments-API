package com.api.v1.utils;

import com.api.v1.dtos.DoctorResponseDto;
import com.api.v1.domain.Doctor;

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
