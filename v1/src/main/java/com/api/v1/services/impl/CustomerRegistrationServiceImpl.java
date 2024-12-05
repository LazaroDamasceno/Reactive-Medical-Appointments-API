package com.api.v1.services.impl;

import com.api.v1.services.CustomerRegistrationService;
import com.api.v1.domain.Customer;
import com.api.v1.domain.CustomerRepository;
import com.api.v1.dtos.CustomerResponseDto;
import com.api.v1.utils.CustomerResponseMapper;
import com.api.v1.dtos.PersonRegistrationDto;
import com.api.v1.services.PersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final PersonRegistrationService personRegistrationService;
    private final CustomerRepository customerRepository;

    public CustomerRegistrationServiceImpl(
            PersonRegistrationService personRegistrationService,
            CustomerRepository customerRepository
    ) {
        this.personRegistrationService = personRegistrationService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<CustomerResponseDto> register(@Valid PersonRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto)
                .flatMap(savedPerson -> {
                    Customer customer = Customer.create(savedPerson);
                    return customerRepository.save(customer);
                })
                .flatMap(savedCustomer -> Mono.just(CustomerResponseMapper.map(savedCustomer)));
    }

}
