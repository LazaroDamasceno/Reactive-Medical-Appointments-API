package com.api.v1.services.impl;

import com.api.v1.services.doctors.DoctorTerminationService;
import com.api.v1.annotations.MedicalLicenseNumber;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.exceptions.doctors.TerminatedDoctorException;
import com.api.v1.utils.doctors.DoctorFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DoctorTerminationServiceImpl implements DoctorTerminationService {

    @Autowired
    private DoctorFinderUtil doctorFinderUtil;

    @Autowired
    private DoctorRepository doctorRepository;

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
