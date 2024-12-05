package com.api.v1.services;

import reactor.core.publisher.Mono;

public interface MedicalAppointmentCancellationService {
    Mono<Void> cancel(String orderNumber);
}
