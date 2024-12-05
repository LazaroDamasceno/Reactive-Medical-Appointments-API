package com.api.v1.doctors;

import com.api.v1.people.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface DoctorModificationService {
    Mono<Void> modify(String medicalLicenseNumber, PersonModificationDto modificationDto);
}
