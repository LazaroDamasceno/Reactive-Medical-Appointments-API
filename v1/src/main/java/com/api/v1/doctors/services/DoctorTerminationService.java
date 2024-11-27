package com.api.v1.doctors.services;

import reactor.core.publisher.Mono;

public interface DoctorTerminationService {
    Mono<Void> terminate(String medicalLicenseNumber);
}
