package com.api.v1.cards;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CardRepository extends ReactiveMongoRepository<Card, ObjectId> {

    @Query("{ 'number': ?0 }")
    Mono<Card> findByNumber(ObjectId number);

}
