package com.api.v1.dtos.medical_slots;

import com.api.v1.dtos.doctors.DoctorResponseDto;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;

import java.time.ZoneId;

public record MedicalSlotResponseDto(
        DoctorResponseDto doctorResponseDto,
        String availableAt,
        ZoneId availableAtZone,
        String canceledAt,
        ZoneId canceledAtZone
) {
}
