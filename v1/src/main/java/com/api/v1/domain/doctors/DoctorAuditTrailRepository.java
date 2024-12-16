package com.api.v1.domain.doctors;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DoctorAuditTrailRepository extends ReactiveMongoRepository<DoctorAuditTrail, ObjectId> {
}
