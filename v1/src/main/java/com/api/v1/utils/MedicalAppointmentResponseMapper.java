package com.api.v1.utils;

import com.api.v1.domain.MedicalAppointment;
import com.api.v1.dtos.MedicalAppointmentResponseDto;
import reactor.core.publisher.Mono;

public class MedicalAppointmentResponseMapper {

    public static MedicalAppointmentResponseDto mapToDto(MedicalAppointment medicalAppointment) {
        return new MedicalAppointmentResponseDto(
                medicalAppointment.getOrderNumber(),
                medicalAppointment.getBookedAt(),
                medicalAppointment.getBookedAtZone(),
                medicalAppointment.getCanceledAt(),
                medicalAppointment.getCanceledAtZone(),
                medicalAppointment.getCompletedAt(),
                medicalAppointment.getCompletedAtZone(),
                CustomerResponseMapper.map(medicalAppointment.getCustomer()),
                DoctorResponseMapper.map(medicalAppointment.getDoctor())
        );
    }

    public static Mono<MedicalAppointmentResponseDto> mapToMono(MedicalAppointment medicalAppointment) {
        return Mono.just(mapToDto(medicalAppointment));
    }

}
