package com.api.v1.services;

import com.api.v1.dtos.CardRegistrationDto;
import com.api.v1.dtos.CardResponseDto;
import reactor.core.publisher.Mono;

public interface CardRegistrationService {
    Mono<CardResponseDto> registerCreditCard(CardRegistrationDto registrationDto);
    Mono<CardResponseDto> registerDebitCard(CardRegistrationDto registrationDto);
}
