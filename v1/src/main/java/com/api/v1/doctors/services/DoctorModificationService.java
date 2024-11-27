package com.api.v1.doctors.services;

import com.api.v1.people.dtos.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface DoctorModificationService {
    Mono<Void> modify(String medicalLicenseNumber, PersonModificationDto modificationDto);
}
