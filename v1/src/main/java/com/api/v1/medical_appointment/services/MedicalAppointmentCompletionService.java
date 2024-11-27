package com.api.v1.medical_appointment.services;

import reactor.core.publisher.Mono;

public interface MedicalAppointmentCompletionService {
    Mono<Void> complete(String orderNumber);
}
