package com.api.v1.services;

import reactor.core.publisher.Mono;

public interface CardDeletionService {
    Mono<Void> deleteByCardNumber(String cardNumber);
}
