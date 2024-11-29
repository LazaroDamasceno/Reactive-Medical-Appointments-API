package com.api.v1.medical_appointments.utils;

import com.api.v1.customers.utils.CustomerResponseMapper;
import com.api.v1.doctors.utils.DoctorResponseMapper;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import com.api.v1.medical_appointments.dtos.MedicalAppointmentResponseDto;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class MedicalAppointmentResponseMapper {

    public static MedicalAppointmentResponseDto mapToDto(MedicalAppointment medicalAppointment) {
        return new MedicalAppointmentResponseDto(
                medicalAppointment.getOrderNumber(),
                OffsetDateTime.of(
                        LocalDateTime.parse(medicalAppointment.getBookedAt().toString()),
                        medicalAppointment.getBookedAtZone()
                ),
                OffsetDateTime.of(
                        LocalDateTime.parse(medicalAppointment.getCanceledAt().toString()),
                        medicalAppointment.getCanceledAtZone()
                ),
                OffsetDateTime.of(
                        LocalDateTime.parse(medicalAppointment.getCompletedAt().toString()),
                        medicalAppointment.getCompletedAtZone()
                ),
                CustomerResponseMapper.map(medicalAppointment.getCustomer()),
                DoctorResponseMapper.map(medicalAppointment.getDoctor())
        );
    }

    public static Mono<MedicalAppointmentResponseDto> mapToMono(MedicalAppointment medicalAppointment) {
        return Mono.just(mapToDto(medicalAppointment));
    }

}
