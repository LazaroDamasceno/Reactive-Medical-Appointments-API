package com.api.v1.services.impl.customers;

import com.api.v1.services.interfaces.customers.CustomerRegistrationService;
import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.customers.CustomerRepository;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.utils.customers.CustomerResponseMapper;
import com.api.v1.dtos.people.PersonRegistrationDto;
import com.api.v1.services.interfaces.people.PersonRegistrationService;
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
