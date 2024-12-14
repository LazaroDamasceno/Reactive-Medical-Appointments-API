package com.api.v1.utils.doctors;

import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.exceptions.doctors.NonExistentDoctorException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DoctorFinderUtil {

    private final DoctorRepository doctorRepository;

    public DoctorFinderUtil(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Mono<Doctor> find(String licenseNumber) {
        return doctorRepository
                .findByLicenseNumber(licenseNumber)
                .switchIfEmpty(Mono.error(NonExistentDoctorException::new))
                .single();
    }

}
