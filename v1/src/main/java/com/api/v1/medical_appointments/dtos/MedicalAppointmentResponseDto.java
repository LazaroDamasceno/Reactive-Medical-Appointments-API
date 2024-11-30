package com.api.v1.medical_appointments.dtos;

import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.doctors.dtos.DoctorResponseDto;
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
