package com.api.v1.services.medical_slots;

import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MedicalSlotRetrievalService {
    Mono<MedicalSlotResponseDto> find(String id);
    Flux<MedicalSlotResponseDto> findAll();
    Flux<MedicalSlotResponseDto> findAll(String medicalLicenseNumber);
    Flux<MedicalSlotResponseDto> findactivated(String medicalLicenseNumber);
    Flux<MedicalSlotResponseDto> findCompleted(String medicalLicenseNumber);
    Flux<MedicalSlotResponseDto> findCanceled(String medicalLicenseNumber);
}
