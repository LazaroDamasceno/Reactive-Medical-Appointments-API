package com.api.v1.services.medical_appointments;

import reactor.core.publisher.Mono;

public interface MedicalAppointmentCancellationService {
    Mono<Void> cancel(String id);
}
