package com.api.v1.cards.services;

import reactor.core.publisher.Mono;

public interface CardDeletionService {
    Mono<Void> deleteByCardNumber(String cardNumber);
}
