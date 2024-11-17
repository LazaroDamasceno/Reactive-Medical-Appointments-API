package com.api.v1.doctors.services;

import com.api.v1.doctors.dtos.DoctorModificationDto;
import reactor.core.publisher.Mono;

public interface DoctorModificationService {
    Mono<Void> modify(DoctorModificationDto modificationDto);
}
