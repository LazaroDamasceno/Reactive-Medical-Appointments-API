package com.api.v1.doctors;

import com.api.v1.doctors.exceptions.NonExistentDoctorException;
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
                .singleOptional()
                .flatMap(optional -> {
                   if (optional.isEmpty()) {
                       return Mono.error(NonExistentDoctorException::new);
                   }
                   return Mono.just(optional.get());
                });
    }

}
