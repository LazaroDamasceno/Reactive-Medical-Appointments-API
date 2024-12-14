package com.api.v1.utils.medical_slots;

import com.api.v1.domain.medical_slots.MedicalSlot;
import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import com.api.v1.utils.doctors.DoctorResponseMapper;
import com.api.v1.utils.medical_appointments.MedicalAppointmentResponseMapper;
import reactor.core.publisher.Mono;

public class MedicalSlotResponseMapper {

    public static MedicalSlotResponseDto mapToDto(MedicalSlot medicalSlot) {
        return new MedicalSlotResponseDto(
                DoctorResponseMapper.mapToDto(medicalSlot.getDoctor()),
                medicalSlot.getAvailableAt(),
                medicalSlot.getAvailableAtZone(),
                medicalSlot.getCanceledAt(),
                medicalSlot.getCanceledAtZone()
        );
    }

    public static Mono<MedicalSlotResponseDto> mapToMono(MedicalSlot medicalSlot) {
        return Mono.just(mapToDto(medicalSlot));
    }

}
