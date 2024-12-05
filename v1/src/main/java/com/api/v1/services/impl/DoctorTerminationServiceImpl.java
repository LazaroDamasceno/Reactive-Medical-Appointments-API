package com.api.v1.services.impl;

import com.api.v1.services.DoctorTerminationService;
import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.DoctorRepository;
import com.api.v1.exceptions.TerminatedDoctorException;
import com.api.v1.utils.DoctorFinderUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorTerminationServiceImpl implements DoctorTerminationService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;

    public DoctorTerminationServiceImpl(DoctorFinderUtil doctorFinderUtil, DoctorRepository doctorRepository) {
        this.doctorFinderUtil = doctorFinderUtil;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Mono<Void> terminate(@MedicalLicenseNumber String medicalLicenseNumber) {
        return doctorFinderUtil
                .find(medicalLicenseNumber)
                .flatMap(doctor -> {
                    if (doctor.getTerminatedAt() != null) {
                        return Mono.error(TerminatedDoctorException::new);
                    }
                    doctor.terminate();
                    return doctorRepository.save(doctor);
                })
                .then();
    }

}