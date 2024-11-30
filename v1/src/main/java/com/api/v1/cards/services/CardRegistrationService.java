package com.api.v1.cards.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.dtos.CardRegistrationDto;
import com.api.v1.cards.dtos.CardResponseDto;
import reactor.core.publisher.Mono;

public interface CardRegistrationService {
    Mono<CardResponseDto> registerCreditCard(CardRegistrationDto registrationDto);
    Mono<CardResponseDto> registerDebitCard(CardRegistrationDto registrationDto);
}
