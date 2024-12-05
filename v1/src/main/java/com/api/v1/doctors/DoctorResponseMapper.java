package com.api.v1.doctors;

import com.api.v1.people.PersonResponseMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DoctorResponseMapper {

    public DoctorResponseDto map(Doctor doctor) {
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
