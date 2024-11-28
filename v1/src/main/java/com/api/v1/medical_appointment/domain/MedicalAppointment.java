package com.api.v1.medical_appointment.domain;

import com.api.v1.customers.domain.Customer;
import com.api.v1.doctors.domain.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
    private LocalDateTime bookedAt;
    private ZoneId bookedAtZone;
    private LocalDateTime canceledAt;
    private ZoneId canceledAtZone;
    private LocalDateTime completedAt;
    private ZoneId completedAtZone;
    private LocalDateTime createdAt;
    private ZoneId createdAtZone;

    private MedicalAppointment(Customer customer, Doctor doctor, LocalDateTime bookedAt, String type) {
        this.id = new ObjectId();
        this.type = type;
        this.orderNumber = new ObjectId();
        this.createdAt = LocalDateTime.now();
        this.createdAtZone = ZoneId.systemDefault();
        this.bookedAt = bookedAt;
        this.bookedAtZone = ZoneId.systemDefault();
        this.doctor = doctor;
        this.customer = customer;
    }

    public static MedicalAppointment create(Customer customer, Doctor doctor, LocalDateTime bookedAt, String type) {
        return new MedicalAppointment(customer, doctor, bookedAt, type);
    }

    public void markAsCompleted() {
        completedAt = LocalDateTime.now();
        completedAtZone = ZoneId.systemDefault();
    }

    public void markAsCanceled() {
        canceledAt = LocalDateTime.now();
        canceledAtZone = ZoneId.systemDefault();
    }

}
