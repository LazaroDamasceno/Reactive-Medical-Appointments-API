package com.api.v1.services.customers.impl;

import com.api.v1.annotations.SSN;
import com.api.v1.domain.customers.CustomerRepository;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.services.customers.CustomerRetrievalService;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.utils.customers.CustomerResponseMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerRetrievalServiceImpl implements CustomerRetrievalService {

    private final CustomerRepository customerRepository;
    private final CustomerFinderUtil customerFinderUtil;

    public CustomerRetrievalServiceImpl(
            CustomerRepository customerRepository,
            CustomerFinderUtil customerFinderUtil
    ) {
        this.customerRepository = customerRepository;
        this.customerFinderUtil = customerFinderUtil;
    }

    @Override
    public Mono<CustomerResponseDto> findBySsn(@SSN String ssn) {
        return customerFinderUtil
                .find(ssn)
                .flatMap(CustomerResponseMapper::mapToMono);
    }

    @Override
    public Flux<CustomerResponseDto> findAll() {
        return customerRepository
                .findAll()
                .map(CustomerResponseMapper::mapToDto);
    }
}
