package com.api.v1.services.doctors;

import com.api.v1.dtos.doctors.DoctorResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DoctorRetrievalService {
    Mono<DoctorResponseDto> findByMedicalLicenseNumber(String medicalLicenseNumber);
    Flux<DoctorResponseDto> findAll();
}
