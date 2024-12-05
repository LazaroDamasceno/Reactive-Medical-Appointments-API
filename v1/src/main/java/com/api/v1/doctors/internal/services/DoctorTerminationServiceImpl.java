package com.api.v1.doctors.internal.services;

import com.api.v1.doctors.DoctorTerminationService;
import com.api.v1.doctors.MedicalLicenseNumber;
import com.api.v1.doctors.DoctorRepository;
import com.api.v1.doctors.internal.exceptions.TerminatedDoctorException;
import com.api.v1.doctors.DoctorFinderUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class DoctorTerminationServiceImpl implements DoctorTerminationService {

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
