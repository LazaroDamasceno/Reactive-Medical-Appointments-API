package com.api.v1.services.doctors;

import reactor.core.publisher.Mono;

public interface DoctorTerminationService {
    Mono<Void> terminate(String medicalLicenseNumber);
}
