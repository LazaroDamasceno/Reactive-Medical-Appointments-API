package com.api.v1.doctors.services;

import com.api.v1.doctors.dtos.DoctorModificationDto;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface DoctorModificationService {
    Mono<Void> modify(@Valid DoctorModificationDto modificationDto);
}
