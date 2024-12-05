package com.api.v1.customers;

import com.api.v1.people.PersonModificationDto;
import reactor.core.publisher.Mono;

public interface CustomerModificationService {
    Mono<Void> modify(String ssn, PersonModificationDto modificationDto);
}
