package com.api.v1.cards.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CardRepository extends ReactiveMongoRepository<Card, UUID> {

    @Query("{ 'number': ?0 }")
    Mono<Card> findByNumber(ObjectId number);

}
