package com.api.v1.services.doctors;

import reactor.core.publisher.Mono;

public interface DoctorRehiringService {
    Mono<Void> rehire(String medicalLicenseNumber);
}
