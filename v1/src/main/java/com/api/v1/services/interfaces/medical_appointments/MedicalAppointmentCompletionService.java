package com.api.v1.services.interfaces.medical_appointments;

import reactor.core.publisher.Mono;

public interface MedicalAppointmentCompletionService {
    Mono<Void> complete(String orderNumber);
}
