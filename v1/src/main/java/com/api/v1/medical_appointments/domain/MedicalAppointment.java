package com.api.v1.medical_appointments.domain;

import com.api.v1.customers.domain.Customer;
import com.api.v1.doctors.domain.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.BsonDateTime;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

@Document
@NoArgsConstructor
@Getter
public class MedicalAppointment {

    @BsonId
    private ObjectId id;
    private ObjectId orderNumber;
    private String type;
    private Customer customer;
    private Doctor doctor;
    private BsonDateTime bookedAt;
    private ZoneOffset bookedAtZone;
    private BsonDateTime canceledAt;
    private ZoneOffset canceledAtZone;
    private BsonDateTime completedAt;
    private ZoneOffset completedAtZone;
    private BsonDateTime createdAt;
    private ZoneOffset createdAtZone;

    private MedicalAppointment(Customer customer, Doctor doctor, LocalDateTime bookedAt, String type) {
        this.id = new ObjectId();
        this.type = type;
        this.orderNumber = new ObjectId();
        this.createdAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        this.createdAtZone = ZoneOffset.UTC;
        this.bookedAt = new BsonDateTime(bookedAt.toInstant(ZoneOffset.UTC).toEpochMilli());
        this.bookedAtZone = ZoneOffset.UTC;
        this.doctor = doctor;
        this.customer = customer;
    }

    public static MedicalAppointment create(Customer customer, Doctor doctor, LocalDateTime bookedAt, String type) {
        return new MedicalAppointment(customer, doctor, bookedAt, type);
    }

    public void markAsCompleted() {
        completedAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        completedAtZone = ZoneOffset.UTC;
    }

    public void markAsCanceled() {
        canceledAt = new BsonDateTime(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        canceledAtZone = ZoneOffset.UTC;
    }

}
