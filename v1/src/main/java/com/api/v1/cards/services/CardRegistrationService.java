package com.api.v1.cards.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.dtos.CardRegistrationDto;
import reactor.core.publisher.Mono;

public interface CardRegistrationService {
    Mono<Card> registerCreditCard(CardRegistrationDto registrationDto);
    Mono<Card> registerDebitCard(CardRegistrationDto registrationDto);
}
