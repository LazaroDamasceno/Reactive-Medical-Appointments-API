package com.api.v1.services.medical_slots;

import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface MedicalSlotCompletionService {
    Mono<Void> complete(String id);
    Mono<Void> complete(ObjectId id);
}
