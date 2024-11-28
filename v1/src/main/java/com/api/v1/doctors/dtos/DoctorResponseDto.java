package com.api.v1.doctors.dtos;

import com.api.v1.people.dtos.PersonResponseDto;
import java.util.Date;

public record DoctorResponseDto(
        String licenseNumber,
        PersonResponseDto personResponseDto,
        Date hiredAt,
        Date terminatedAt
) {
}
