package com.api.v1.cards;

import reactor.core.publisher.Mono;

public interface CardDeletionService {
    Mono<Void> deleteByCardNumber(String cardNumber);
}
