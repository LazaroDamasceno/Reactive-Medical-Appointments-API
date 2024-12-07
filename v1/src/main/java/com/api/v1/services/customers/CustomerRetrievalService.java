package com.api.v1.services.customers;

import com.api.v1.dtos.customers.CustomerResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRetrievalService {
    Mono<CustomerResponseDto> findBySsn(String ssn);
    Flux<CustomerResponseDto> findAll();
}
