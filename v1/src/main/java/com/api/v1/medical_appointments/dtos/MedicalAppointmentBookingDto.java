package com.api.v1.medical_appointments.dtos;

import java.time.LocalDateTime;

public record MedicalAppointmentBookingDto(
        String ssn,
        String medicalLicenseNumber,
        LocalDateTime bookingDate
) {
}
