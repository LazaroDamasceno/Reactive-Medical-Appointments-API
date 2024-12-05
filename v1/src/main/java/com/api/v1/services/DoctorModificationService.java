package com.api.v1.services;

import com.api.v1.dtos.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface DoctorModificationService {
    Mono<Void> modify(String medicalLicenseNumber, PersonModificationDto modificationDto);
}
