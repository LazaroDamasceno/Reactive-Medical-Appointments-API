package com.api.v1.customers.services;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.dtos.CustomerResponseDto;
import com.api.v1.customers.utils.CustomerResponseMapper;
import com.api.v1.db.DbSets;
import com.api.v1.people.dtos.PersonRegistrationDto;
import com.api.v1.people.services.PersonRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final PersonRegistrationService personRegistrationService;

    @Override
    public Mono<CustomerResponseDto> register(@Valid PersonRegistrationDto registrationDto) {
        return personRegistrationService
                .register(registrationDto)
                .flatMap(personId -> {
                    Customer customer = Customer.create(personId);
                    DbSets.customerCollection()
                            .document()
                            .set(customer);
                    try {
                        return CustomerResponseMapper.mapToMono(customer);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
