package com.api.v1.doctors.services;

import com.api.v1.doctors.dtos.DoctorRegistrationDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import reactor.core.publisher.Mono;

public interface DoctorRegistrationService {
    Mono<DoctorResponseDto> register(DoctorRegistrationDto registrationDto);
}
