package com.api.v1.people.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonAuditTrailRepository extends ReactiveMongoRepository<PersonAuditTrail, ObjectId> {
}
