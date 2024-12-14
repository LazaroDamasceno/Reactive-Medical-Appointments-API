package com.api.v1.dtos.medical_appointments;

import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.dtos.doctors.DoctorResponseDto;

import java.time.ZoneId;

public record MedicalAppointmentResponseDto(
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
