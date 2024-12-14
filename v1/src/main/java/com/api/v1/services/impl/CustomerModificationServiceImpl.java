package com.api.v1.services.impl;

import com.api.v1.services.customers.CustomerModificationService;
import com.api.v1.domain.customers.CustomerRepository;
import com.api.v1.utils.customers.CustomerFinderUtil;
import com.api.v1.annotations.SSN;
import com.api.v1.dtos.people.PersonModificationDto;
import com.api.v1.services.people.PersonModificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerModificationServiceImpl implements CustomerModificationService {

    @Autowired
    private CustomerFinderUtil customerFinderUtil;

    @Autowired
    private PersonModificationService personModificationService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Void> modify(@SSN String ssn, @Valid PersonModificationDto modificationDto) {
        return customerFinderUtil
                .find(ssn)
                .flatMap(customer -> personModificationService
                        .modify(customer.getPerson(), modificationDto)
                        .flatMap(modifiedPerson -> {
                            customer.setPerson(modifiedPerson);
                            return customerRepository.save(customer);
                        })
                ).then();
    }

}
