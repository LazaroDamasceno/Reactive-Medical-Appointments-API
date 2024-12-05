package com.api.v1.doctors;

import com.api.v1.doctors.exceptions.NonExistentDoctorException;
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
                .singleOptional()
                .flatMap(optional -> {
                   if (optional.isEmpty()) {
                       return Mono.error(NonExistentDoctorException::new);
                   }
                   return Mono.just(optional.get());
                });
    }

}
