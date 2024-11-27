package com.api.v1.doctors.utils;

import com.api.v1.doctors.exceptions.NonExistentDoctorException;
import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.domain.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DoctorFinderUtil {

    private final DoctorRepository doctorRepository;

    public Mono<Doctor> find(String licenseNumber) {
        return doctorRepository
                .findByLicenseNumber(licenseNumber)
                .hasElement()
                .flatMap(exists -> {
                    if (!exists) return Mono.error(NonExistentDoctorException::new);
                    return doctorRepository
                            .findByLicenseNumber(licenseNumber)
                            .flatMap(Mono::just);
                });
    }

}
