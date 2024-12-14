package com.api.v1.services.medical_slots;

import reactor.core.publisher.Mono;

public interface MedicalSlotCompletionService {
    Mono<Void> complete(String id);
}
