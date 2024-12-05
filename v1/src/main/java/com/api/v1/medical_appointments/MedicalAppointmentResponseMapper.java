package com.api.v1.medical_appointments;

import com.api.v1.customers.CustomerResponseMapper;
import com.api.v1.doctors.DoctorResponseMapper;
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
