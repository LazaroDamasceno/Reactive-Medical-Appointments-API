package com.api.v1.services.impl;

import com.api.v1.services.customers.CustomerRegistrationService;
import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.customers.CustomerRepository;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.utils.customers.CustomerResponseMapper;
import com.api.v1.dtos.people.PersonRegistrationDto;
import com.api.v1.services.people.PersonRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    @Autowired
    private PersonRegistrationService personRegistrationService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<CustomerResponseDto> register(@Valid PersonRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto)
                .flatMap(savedPerson -> {
                    Customer customer = Customer.create(savedPerson);
                    return customerRepository.save(customer);
                })
                .flatMap(savedCustomer -> Mono.just(CustomerResponseMapper.mapToDto(savedCustomer)));
    }

}
