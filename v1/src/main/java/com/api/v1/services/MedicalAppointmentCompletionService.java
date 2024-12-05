package com.api.v1.services;

import reactor.core.publisher.Mono;

public interface MedicalAppointmentCompletionService {
    Mono<Void> complete(String orderNumber);
}
