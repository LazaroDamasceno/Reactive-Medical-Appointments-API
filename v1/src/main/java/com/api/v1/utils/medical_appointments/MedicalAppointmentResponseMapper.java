package com.api.v1.utils.medical_appointments;

import com.api.v1.domain.medical_appointments.MedicalAppointment;
import com.api.v1.dtos.medical_appointments.MedicalAppointmentResponseDto;
import com.api.v1.utils.customers.CustomerResponseMapper;
import com.api.v1.utils.doctors.DoctorResponseMapper;
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
                DoctorResponseMapper.mapToDto(medicalAppointment.getDoctor())
        );
    }

    public static Mono<MedicalAppointmentResponseDto> mapToMono(MedicalAppointment medicalAppointment) {
        return Mono.just(mapToDto(medicalAppointment));
    }

}
