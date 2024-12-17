package com.api.v1.services.impl;

import com.api.v1.domain.medical_slots.MedicalSlot;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.dtos.medical_slots.MedicalSlotRegistrationDto;
import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import com.api.v1.exceptions.medical_slots.InvalidMedicalSlotDateTimeException;
import com.api.v1.services.medical_slots.MedicalSlotRegistrationService;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.utils.medical_slots.MedicalSlotResponseMapper;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MedicalSlotRegistrationServiceImpl implements MedicalSlotRegistrationService {

    private final MedicalSlotRepository medicalSlotRepository;
    private final DoctorFinderUtil doctorFinderUtil;

    public MedicalSlotRegistrationServiceImpl(
            MedicalSlotRepository medicalSlotRepository,
            DoctorFinderUtil doctorFinderUtil
    ) {
        this.medicalSlotRepository = medicalSlotRepository;
        this.doctorFinderUtil = doctorFinderUtil;
    }

    @Override
    public Mono<MedicalSlotResponseDto> register(@Valid MedicalSlotRegistrationDto registrationDto) {
        return doctorFinderUtil
                .findByLicenseNumber(registrationDto.medicalLicenseNumber())
                .flatMap(foundDoctor -> {
                    return onDuplicatedDateTime(foundDoctor.getId(), registrationDto.availableAt().toString())
                            .then(Mono.defer(() -> {
                                MedicalSlot medicalSlot = MedicalSlot.create(registrationDto.availableAt(), foundDoctor);
                                return medicalSlotRepository.save(medicalSlot);
                            }));
                })
                .flatMap(MedicalSlotResponseMapper::mapToMono);
    }

    private Mono<Object> onDuplicatedDateTime(ObjectId doctorId, String availableAt) {
        return medicalSlotRepository
                .findAll()
                .filter(medicalSlot ->
                        medicalSlot.getAvailableAt().equals(availableAt)
                        && medicalSlot.getDoctor().getId().equals(doctorId)
                        && medicalSlot.getCanceledAt() == null
                        && medicalSlot.getCompletedAt() == null
                )
                .hasElements()
                .flatMap(exists -> {
                    String message = "There's an occupied medical slot whose datetime is equals to the given one.";
                    if (exists) return Mono.error(new InvalidMedicalSlotDateTimeException(message));
                    return Mono.empty();
                });
    }
}
