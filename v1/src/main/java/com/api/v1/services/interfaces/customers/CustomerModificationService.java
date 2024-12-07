package com.api.v1.services.interfaces.customers;

import com.api.v1.dtos.people.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {
    Mono<Void> modify(String ssn, PersonModificationDto modificationDto);
}
