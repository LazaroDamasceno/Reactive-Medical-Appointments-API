package com.api.v1.medical_appointment.domain;

import com.api.v1.customers.domain.Customer;
import com.api.v1.doctors.domain.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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
    private Date bookedAt;
    private ZoneId bookedAtZone;
    private Date canceledAt;
    private ZoneId canceledAtZone;
    private Date completedAt;
    private ZoneId completedAtZone;
    private Date createdAt;
    private ZoneId createdAtZone;

    private MedicalAppointment(Customer customer, Doctor doctor, Date bookedAt, String type) {
        this.id = new ObjectId();
        this.type = type;
        this.orderNumber = new ObjectId();
        this.createdAt = new Date();
        this.createdAtZone = ZoneId.systemDefault();
        this.bookedAt = bookedAt;
        this.bookedAtZone = ZoneId.systemDefault();
        this.doctor = doctor;
        this.customer = customer;
    }

    public static MedicalAppointment create(Customer customer, Doctor doctor, Date bookedAt, String type) {
        return new MedicalAppointment(customer, doctor, bookedAt, type);
    }

    public void markAsCompleted() {
        completedAt = new Date();
        completedAtZone = ZoneId.systemDefault();
    }

    public void markAsCanceled() {
        canceledAt = new Date();
        canceledAtZone = ZoneId.systemDefault();
    }

}
