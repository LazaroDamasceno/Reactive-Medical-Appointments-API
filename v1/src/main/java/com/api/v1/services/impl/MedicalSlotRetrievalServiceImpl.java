package com.api.v1.services.impl;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.annotations.MongoDbId;
import com.api.v1.domain.medical_slots.MedicalSlotRepository;
import com.api.v1.dtos.medical_slots.MedicalSlotResponseDto;
import com.api.v1.services.medical_slots.MedicalSlotRetrievalService;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.utils.medical_slots.MedicalSlotFinderUtil;
import com.api.v1.utils.medical_slots.MedicalSlotResponseMapper;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MedicalSlotRetrievalServiceImpl implements MedicalSlotRetrievalService {

    private final MedicalSlotFinderUtil medicalSlotFinderUtil;
    private final MedicalSlotRepository medicalSlotRepository;
    private final DoctorFinderUtil doctorFinderUtil;

    public MedicalSlotRetrievalServiceImpl(
            MedicalSlotFinderUtil medicalSlotFinderUtil,
            MedicalSlotRepository medicalSlotRepository,
            DoctorFinderUtil doctorFinderUtil
    ) {
        this.medicalSlotFinderUtil = medicalSlotFinderUtil;
        this.medicalSlotRepository = medicalSlotRepository;
        this.doctorFinderUtil = doctorFinderUtil;
    }

    @Override
    public Mono<MedicalSlotResponseDto> find(@MongoDbId String id) {
        return medicalSlotFinderUtil
                .find(new ObjectId(id))
                .flatMap(MedicalSlotResponseMapper::mapToMono);
    }

    @Override
    public Flux<MedicalSlotResponseDto> findAll() {
        return medicalSlotRepository
                .findAll()
                .flatMap(slot -> Flux.just(MedicalSlotResponseMapper.mapToDto(slot)));
    }

    @Override
    public Flux<MedicalSlotResponseDto> findAll(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMapMany(doctor -> medicalSlotRepository
                            .findAll()
                            .filter(slot -> slot.getDoctor().getId().equals(doctor.getId()))
                            .flatMap(slot -> Flux.just(MedicalSlotResponseMapper.mapToDto(slot)))
                );
    }

    @Override
    public Flux<MedicalSlotResponseDto> findactivated(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMapMany(doctor -> medicalSlotRepository
                        .findAll()
                        .filter(slot -> slot.getDoctor().getId().equals(doctor.getId())
                                && slot.getCompletedAt() == null
                                && slot.getCanceledAt() == null
                        )
                        .flatMap(slot -> Flux.just(MedicalSlotResponseMapper.mapToDto(slot)))
                );
    }

    @Override
    public Flux<MedicalSlotResponseDto> findCompleted(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMapMany(doctor -> medicalSlotRepository
                        .findAll()
                        .filter(slot -> slot.getDoctor().getId().equals(doctor.getId())
                                && slot.getCompletedAt() != null
                                && slot.getCanceledAt() == null
                        )
                        .flatMap(slot -> Flux.just(MedicalSlotResponseMapper.mapToDto(slot)))
                );
    }

    @Override
    public Flux<MedicalSlotResponseDto> findCanceled(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMapMany(doctor -> medicalSlotRepository
                        .findAll()
                        .filter(slot -> slot.getDoctor().getId().equals(doctor.getId())
                                && slot.getCompletedAt() == null
                                && slot.getCanceledAt() != null
                        )
                        .flatMap(slot -> Flux.just(MedicalSlotResponseMapper.mapToDto(slot)))
                );
    }
}
