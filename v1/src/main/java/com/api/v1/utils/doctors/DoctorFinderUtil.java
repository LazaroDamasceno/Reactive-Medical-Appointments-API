package com.api.v1.utils.doctors;

import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.exceptions.doctors.NonExistentDoctorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DoctorFinderUtil {

    @Autowired
    private DoctorRepository doctorRepository;

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
