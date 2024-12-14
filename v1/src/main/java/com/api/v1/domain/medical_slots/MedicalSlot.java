package com.api.v1.domain.medical_slots;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document
public class MedicalSlot {

    private ObjectId id;
    private String availableAt;
    private ZoneId availableAtZone;
    private String canceledAt;
    private ZoneId canceledAtZone;
    private String createdAt;
    private ZoneId createdAtZone;

    public MedicalSlot() {
    }

    private MedicalSlot(LocalDateTime availableAt) {
        this.id = new ObjectId();
        this.availableAt = availableAt.toString();
        this.availableAtZone = ZoneId.systemDefault();
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static MedicalSlot create(LocalDateTime availableAt) {
        return new MedicalSlot(availableAt);
    }

    public void cancel() {
        canceledAt = LocalDateTime.now().toString();
        createdAtZone = ZoneId.systemDefault();
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
}
