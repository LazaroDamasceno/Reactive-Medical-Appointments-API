package com.api.v1.customers;

import com.api.v1.people.SSN;
import com.api.v1.people.PersonFinderUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerFinderUtil {

    private final PersonFinderUtil personFinderUtil;
    private final CustomerRepository customerRepository;

    public CustomerFinderUtil(PersonFinderUtil personFinderUtil, CustomerRepository customerRepository) {
        this.personFinderUtil = personFinderUtil;
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> find(@SSN String ssn) {
        return personFinderUtil
                .find(ssn)
                .flatMap(customerRepository::findByPerson);
    }

}
