package com.api.v1.services.medical_slots;

import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.domain.medical_slots.MedicalSlot;
import reactor.core.publisher.Mono;

public interface MedicalSlotCompletionService {
    Mono<Void> complete(String id);
    Mono<Void> complete(MedicalSlot medicalSlot, MedicalAppointment medicalAppointment);
}
