package com.api.v1.domain.doctors;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document
public class DoctorAuditTrail {

    @BsonId
    private ObjectId id;
    private Doctor doctor;
    private String createdAt;
    private ZoneId createdAtZone;

    public DoctorAuditTrail() {
    }

    private DoctorAuditTrail(Doctor doctor) {
        this.id = new ObjectId();
        this.doctor = doctor;
        this.createdAt = LocalDateTime.now().toString();
        this.createdAtZone = ZoneId.systemDefault();
    }

    public static DoctorAuditTrail create(Doctor doctor) {
        return new DoctorAuditTrail(doctor);
    }

    public ObjectId getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ZoneId getCreatedAtZone() {
        return createdAtZone;
    }
}
