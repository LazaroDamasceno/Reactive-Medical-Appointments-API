package com.api.v1.services.doctors;

import com.api.v1.dtos.people.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface DoctorModificationService {
    Mono<Void> modify(String medicalLicenseNumber, PersonModificationDto modificationDto);
}
