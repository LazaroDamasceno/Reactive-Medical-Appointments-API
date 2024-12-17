package com.api.v1.utils.doctors;

import com.api.v1.domain.doctors.Doctor;
import com.api.v1.domain.doctors.DoctorRepository;
import com.api.v1.exceptions.doctors.NonExistentDoctorException;
import com.api.v1.exceptions.doctors.TerminatedDoctorException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DoctorFinderUtil {

    private final DoctorRepository doctorRepository;

    public DoctorFinderUtil(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Mono<Doctor> findByLicenseNumber(String licenseNumber) {
        return doctorRepository
                .findByLicenseNumber(licenseNumber)
                .singleOptional()
                .flatMap(optional -> {
                   if (optional.isEmpty()) {
                       return Mono.error(NonExistentDoctorException::new);
                   }
                   if (optional.get().getTerminatedAt() != null) {
                       return Mono.error(TerminatedDoctorException::new);
                   }
                   return Mono.just(optional.get());
                });
    }

    public Mono<Doctor> findByActiveStatusAndLicenseNumber(String licenseNumber) {
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
