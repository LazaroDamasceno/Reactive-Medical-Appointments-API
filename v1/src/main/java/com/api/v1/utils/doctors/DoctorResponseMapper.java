package com.api.v1.utils.doctors;

import com.api.v1.dtos.doctors.DoctorResponseDto;
import com.api.v1.domain.doctors.Doctor;
import com.api.v1.utils.people.PersonResponseMapper;

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
