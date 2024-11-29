package com.api.v1.medical_appointments.dtos;

import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
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
    ZoneId completedAtZone,
    CustomerResponseDto customerResponseDto,
    DoctorResponseDto doctorResponseDto
) {
}
