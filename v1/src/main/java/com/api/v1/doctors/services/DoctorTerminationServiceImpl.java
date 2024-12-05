package com.api.v1.doctors.services;

import com.api.v1.doctors.DoctorTerminationService;
import com.api.v1.doctors.annotations.MedicalLicenseNumber;
import com.api.v1.doctors.DoctorRepository;
import com.api.v1.doctors.exceptions.TerminatedDoctorException;
import com.api.v1.doctors.DoctorFinderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class DoctorTerminationServiceImpl implements DoctorTerminationService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;

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
