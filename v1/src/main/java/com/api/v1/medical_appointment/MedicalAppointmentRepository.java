package com.api.v1.medical_appointment;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentRepository extends ReactiveMongoRepository<MedicalAppointment, ObjectId> {

    @Query("{ 'orderNumber': ?0 }")
    Mono<MedicalAppointment> findByOrderNumber(ObjectId orderNumber);

}
