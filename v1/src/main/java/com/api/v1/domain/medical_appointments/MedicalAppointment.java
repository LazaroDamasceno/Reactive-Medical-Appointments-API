package com.api.v1.domain.medical_appointments;

import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.doctors.Doctor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

@Document
public class MedicalAppointment {

    @BsonId
    private ObjectId id;
    private ObjectId orderNumber;
    private String type;
    private Customer customer;
    private Doctor doctor;
    private String bookedAt;
    private ZoneId bookedAtZone;
    private String canceledAt;
    private ZoneId canceledAtZone;
    private String completedAt;
    private ZoneId completedAtZone;
    private String createdAt;
    private ZoneId createdAtZone;

    private MedicalAppointment(Customer customer, Doctor doctor, LocalDateTime bookedAt, String type) {
        this.id = new ObjectId();
        this.type = type;
        this.orderNumber = new ObjectId();
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
        this.bookedAt = bookedAt.toString();
        this.bookedAtZone = ZoneId.systemDefault();
        this.doctor = doctor;
        this.customer = customer;
    }

    public MedicalAppointment() {
    }

    public static MedicalAppointment create(Customer customer, Doctor doctor, LocalDateTime bookedAt, String type) {
        return new MedicalAppointment(customer, doctor, bookedAt, type);
    }

    public void markAsCompleted() {
        completedAt = LocalDateTime.now().toString();
        completedAtZone = ZoneId.systemDefault();
    }

    public void markAsCanceled() {
        canceledAt = LocalDateTime.now().toString();
        canceledAtZone = ZoneId.systemDefault();
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getOrderNumber() {
        return orderNumber;
    }

    public String getType() {
        return type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getBookedAt() {
        return bookedAt;
    }

    public ZoneId getBookedAtZone() {
        return bookedAtZone;
    }

    public String getCanceledAt() {
        return canceledAt;
    }

    public ZoneId getCanceledAtZone() {
        return canceledAtZone;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public ZoneId getCompletedAtZone() {
        return completedAtZone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ZoneId getCreatedAtZone() {
        return createdAtZone;
    }
}
