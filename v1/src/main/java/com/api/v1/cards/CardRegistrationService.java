package com.api.v1.cards;

import reactor.core.publisher.Mono;

public interface CardRegistrationService {
    Mono<CardResponseDto> registerCreditCard(CardRegistrationDto registrationDto);
    Mono<CardResponseDto> registerDebitCard(CardRegistrationDto registrationDto);
}
