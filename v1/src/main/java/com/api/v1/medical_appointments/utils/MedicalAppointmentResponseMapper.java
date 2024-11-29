package com.api.v1.medical_appointments.utils;

import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto;

public class MedicalAppointmentResponseMapper {

    public static MedicalAppointmentResponseDto map(MedicalAppointment medicalAppointment) {
        return new MedicalAppointmentResponseDto(
                medicalAppointment.getOrderNumber(),
                medicalAppointment.getBookedAt(),
                medicalAppointment.getBookedAtZone(),
                medicalAppointment.getCanceledAt(),
                medicalAppointment.getCanceledAtZone(),
                medicalAppointment.getCompletedAt(),
                medicalAppointment.getCompletedAtZone()
        );
    }

}
