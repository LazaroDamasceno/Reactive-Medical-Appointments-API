package com.api.v1.domain.medical_slots;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface r extends ReactiveMongoRepository<MedicalSlot, ObjectId> {
}
