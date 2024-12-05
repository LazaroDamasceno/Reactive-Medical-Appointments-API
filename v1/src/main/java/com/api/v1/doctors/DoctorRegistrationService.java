package com.api.v1.doctors;

import reactor.core.publisher.Mono;

public interface DoctorRegistrationService {
    Mono<DoctorResponseDto> register(DoctorRegistrationDto registrationDto);
}
