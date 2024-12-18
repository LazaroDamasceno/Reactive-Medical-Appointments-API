package com.api.v1.domain.medical_slots;

import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.medical_appointments.MedicalAppointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document
public class MedicalSlot {

    private ObjectId id;
    private Doctor doctor;
    private String availableAt;
    private ZoneId availableAtZone;
    private MedicalAppointment medicalAppointment;
    private String canceledAt;
    private ZoneId canceledAtZone;
    private String completedAt;
    private ZoneId completedAtZone;
    private String createdAt;
    private ZoneId createdAtZone;

    public MedicalSlot() {
    }

    private MedicalSlot(LocalDateTime availableAt, Doctor doctor) {
        this.id = new ObjectId();
        this.doctor = doctor;
        this.availableAt = availableAt.toString();
        this.availableAtZone = ZoneId.systemDefault();
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static MedicalSlot create(LocalDateTime availableAt, Doctor doctor) {
        return new MedicalSlot(availableAt, doctor);
    }

    public void markAsCanceled() {
        canceledAt = LocalDateTime.now().toString();
        canceledAtZone = ZoneId.systemDefault();
        medicalAppointment = null;
    }

    public void markAsCompleted() {
        completedAt = LocalDateTime.now().toString();
        completedAtZone = ZoneId.systemDefault();
    }

    public ObjectId getId() {
        return id;
    }

    public String getAvailableAt() {
        return availableAt;
    }

    public ZoneId getAvailableAtZone() {
        return availableAtZone;
    }

    public String getCanceledAt() {
        return canceledAt;
    }

    public ZoneId getCanceledAtZone() {
        return canceledAtZone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ZoneId getCreatedAtZone() {
        return createdAtZone;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public ZoneId getCompletedAtZone() {
        return completedAtZone;
    }

    public MedicalAppointment getMedicalAppointment() {
        return medicalAppointment;
    }

    public void setMedicalAppointment(MedicalAppointment medicalAppointment) {
        this.medicalAppointment = medicalAppointment;
    }
}
