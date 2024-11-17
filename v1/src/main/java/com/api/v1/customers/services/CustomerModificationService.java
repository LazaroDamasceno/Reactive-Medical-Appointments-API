package com.api.v1.customers.services;

import com.api.v1.people.dtos.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {
    Mono<Void> modify(String ssn, PersonModificationDto modificationDto);
}
