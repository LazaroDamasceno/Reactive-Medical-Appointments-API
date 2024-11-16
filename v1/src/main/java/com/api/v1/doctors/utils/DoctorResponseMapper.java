package com.api.v1.doctors.utils;

import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.people.utils.PersonFinderUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DoctorResponseMapper {

    public DoctorResponseDto mapToDto(Doctor doctor) throws Exception {
        return new DoctorResponseDto(
                PersonFinderUtil.findById(doctor.personId()),
                doctor.licenseNumber(),
                doctor.createdAt()
        );
    }

}
