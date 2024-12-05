package com.api.v1.services.impl;

import com.api.v1.services.CardRegistrationService;
import com.api.v1.domain.Card;
import com.api.v1.domain.CardRepository;
import com.api.v1.dtos.CardRegistrationDto;
import com.api.v1.dtos.CardResponseDto;
import com.api.v1.utils.CardResponseMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CardRegistrationServiceImpl implements CardRegistrationService {

    private final CardRepository cardRepository;

    public CardRegistrationServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Mono<CardResponseDto> registerCreditCard(@Valid CardRegistrationDto registrationDto) {
        return Mono.defer(() -> {
           Card creditCard = Card.create(
                   "Credit card",
                   registrationDto.dueDate(),
                   registrationDto.cvc(),
                   registrationDto.ownerName(),
                   registrationDto.ssnOwner()
           );
           return cardRepository.save(creditCard);
        })
        .flatMap(CardResponseMapper::mapToMono);
    }

    @Override
    public Mono<CardResponseDto> registerDebitCard(@Valid CardRegistrationDto registrationDto) {
        return Mono.defer(() -> {
            Card debitCard = Card.create(
                    "Debit card",
                    registrationDto.dueDate(),
                    registrationDto.cvc(),
                    registrationDto.ownerName(),
                    registrationDto.ssnOwner()
            );
            return cardRepository.save(debitCard);
        })
        .flatMap(CardResponseMapper::mapToMono);
    }
}
