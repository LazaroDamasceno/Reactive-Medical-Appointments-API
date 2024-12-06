package com.api.v1.domain.medical_appointments;

import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.doctors.Doctor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentRepository extends ReactiveMongoRepository<MedicalAppointment, ObjectId> {

    @Query("{ 'orderNumber': ?0 }")
    Mono<MedicalAppointment> findByOrderNumber(ObjectId orderNumber);

    @Query("""
            { 'customer': ?0 },
            { 'doctor': ?1 },
            { 'bookedAt': ?2 },
            { 'canceledAt': null },
            { 'completedAt': null }
    """)
    Mono<MedicalAppointment> find(Customer customer, Doctor doctor, String bookedAt);

}
