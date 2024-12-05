package com.api.v1.cards.internal.services;

import com.api.v1.cards.CardRegistrationService;
import com.api.v1.cards.Card;
import com.api.v1.cards.CardRepository;
import com.api.v1.cards.CardRegistrationDto;
import com.api.v1.cards.CardResponseDto;
import com.api.v1.cards.CardResponseMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class CardRegistrationServiceImpl implements CardRegistrationService {

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
