package com.api.v1.medical_appointment.dtos;

import java.util.Date;

public record MedicalAppointmentBookingDto(
        String ssn,
        String medicalLicenseNumber,
        Date bookingDate
) {
}
