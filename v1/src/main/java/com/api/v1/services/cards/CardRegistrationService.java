package com.api.v1.services.cards;

import com.api.v1.dtos.cards.CardRegistrationDto;
import com.api.v1.dtos.cards.CardResponseDto;
import reactor.core.publisher.Mono;

public interface CardRegistrationService {
    Mono<CardResponseDto> registerCreditCard(CardRegistrationDto registrationDto);
    Mono<CardResponseDto> registerDebitCard(CardRegistrationDto registrationDto);
}
