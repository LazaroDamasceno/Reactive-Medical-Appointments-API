package com.api.v1.dtos;

import org.bson.types.ObjectId;

import java.time.ZoneId;

public record MedicalAppointmentResponseDto(
    ObjectId orderNumber,
    String bookedAt,
    ZoneId bookedAtZone,
    String canceledAt,
    ZoneId canceledAtZone,
    String completedAt,
    ZoneId completedAtZone,
    CustomerResponseDto customerResponseDto,
    DoctorResponseDto doctorResponseDto
) {
}
