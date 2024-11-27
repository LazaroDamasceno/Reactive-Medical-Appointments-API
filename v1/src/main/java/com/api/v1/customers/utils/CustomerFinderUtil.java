package com.api.v1.customers.utils;

import com.api.v1.customers.domain.Customer;
import com.api.v1.customers.domain.CustomerRepository;
import com.api.v1.people.annotations.SSN;
import com.api.v1.people.utils.PersonFinderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerFinderUtil {

    private final PersonFinderUtil personFinderUtil;
    private final CustomerRepository customerRepository;

    public Mono<Customer> find(@SSN String ssn) {
        return personFinderUtil
                .find(ssn)
                .flatMap(customerRepository::findByPerson);
    }

}
