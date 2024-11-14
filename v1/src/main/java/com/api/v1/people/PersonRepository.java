package com.api.v1.people;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PersonRepository extends ReactiveMongoRepository<Person, UUID> {

    @Query("{ 'ssn': ?0 }")
    Mono<Person> findBySsn(String ssn);

    @Query("{ 'email': ?0 }")
    Mono<Person> findByEmail(String email);

}
