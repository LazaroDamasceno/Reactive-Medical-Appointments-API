package com.api.v1.medical_appointments.dtos;

import org.bson.BsonDateTime;
import org.bson.types.ObjectId;

import java.time.ZoneId;

public record MedicalAppointmentResponseDto(
    ObjectId orderNumber,
    BsonDateTime bookedAt,
    ZoneId bookedAtZone,
    BsonDateTime canceledAt,
    ZoneId canceledAtZone,
    BsonDateTime completedAt,
    ZoneId completedAtZone
) {
}
