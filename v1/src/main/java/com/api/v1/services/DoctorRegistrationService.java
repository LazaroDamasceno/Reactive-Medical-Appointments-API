package com.api.v1.services;

import com.api.v1.dtos.DoctorRegistrationDto;
import com.api.v1.dtos.DoctorResponseDto;
import reactor.core.publisher.Mono;

public interface DoctorRegistrationService {
    Mono<DoctorResponseDto> register(DoctorRegistrationDto registrationDto);
}
