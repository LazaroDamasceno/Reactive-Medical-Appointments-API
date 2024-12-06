package com.api.v1.services.interfaces.doctors;

import com.api.v1.dtos.doctors.DoctorRegistrationDto;
import com.api.v1.dtos.doctors.DoctorResponseDto;
import reactor.core.publisher.Mono;

public interface DoctorRegistrationService {
    Mono<DoctorResponseDto> register(DoctorRegistrationDto registrationDto);
}
