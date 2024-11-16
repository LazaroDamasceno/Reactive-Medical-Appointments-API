package com.api.v1.customers.domain;

import com.api.v1.people.domain.Person;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, UUID> {

    @Query("{ 'person': ?0 }")
    Mono<Customer> findByPerson(Person person);

}
