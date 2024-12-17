package com.api.v1.services.impl;

import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.dtos.doctors.DoctorResponseDto;
import com.api.v1.services.doctors.DoctorRetrievalService;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import com.api.v1.utils.doctors.DoctorResponseMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DoctorRetrievalServiceImpl implements DoctorRetrievalService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;

    public DoctorRetrievalServiceImpl(
            DoctorFinderUtil doctorFinderUtil,
            DoctorRepository doctorRepository
    ) {
        this.doctorFinderUtil = doctorFinderUtil;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Mono<DoctorResponseDto> findByMedicalLicenseNumber(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMap(DoctorResponseMapper::mapToMono);
    }

    @Override
    public Flux<DoctorResponseDto> findAll() {
        return doctorRepository
                .findAll()
                .map(DoctorResponseMapper::mapToDto);
    }
}
