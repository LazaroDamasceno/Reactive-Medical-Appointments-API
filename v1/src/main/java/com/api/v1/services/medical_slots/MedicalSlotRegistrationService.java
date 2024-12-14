package com.api.v1.services.medical_slots;

import com.api.v1.dtos.medical_slots.MedicalSlotRegistrationDto;
import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import reactor.core.publisher.Mono;

public interface MedicalSlotRegistrationService {
    Mono<MedicalSlotResponseDto> register(MedicalSlotRegistrationDto registrationDto);
}
