package com.api.v1.services.doctors;

import com.api.v1.dtos.doctors.DoctorHiringDto;
import com.api.v1.dtos.doctors.DoctorResponseDto;
import reactor.core.publisher.Mono;

public interface DoctorHiringService {
    Mono<DoctorResponseDto> hire(DoctorHiringDto registrationDto);
}
