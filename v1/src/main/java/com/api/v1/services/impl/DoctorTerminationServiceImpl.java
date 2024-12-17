package com.api.v1.services.impl;

import com.api.v1.services.doctors.DoctorTerminationService;
import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.exceptions.doctors.TerminatedDoctorException;
import com.api.v1.utils.doctors.DoctorFinderUtil;
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
                .findByLicenseNumber(medicalLicenseNumber)
                .flatMap(doctor -> {
                    if (doctor.getTerminatedAt() != null) {
                        return Mono.error(TerminatedDoctorException::new);
                    }
                    doctor.markAsTerminated();
                    return doctorRepository.save(doctor);
                })
                .then();
    }

}
