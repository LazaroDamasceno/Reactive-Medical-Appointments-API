package com.api.v1.utils.customers;

import com.api.v1.annotations.SSN;
import com.api.v1.domain.customers.Customer;
import com.api.v1.domain.customers.CustomerRepository;
import com.api.v1.utils.people.PersonFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerFinderUtil {

    @Autowired
    private PersonFinderUtil personFinderUtil;

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<Customer> find(@SSN String ssn) {
        return personFinderUtil
                .find(ssn)
                .flatMap(customerRepository::findByPerson);
    }

}
