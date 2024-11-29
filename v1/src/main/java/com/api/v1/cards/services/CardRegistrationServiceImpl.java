package com.api.v1.cards.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.domain.CardRepository;
import com.api.v1.cards.dtos.CardRegistrationDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Service
@RequiredArgsConstructor
class CardRegistrationServiceImpl implements CardRegistrationService {

    private final CardRepository cardRepository;

    @Override
    public Mono<Card> registerCreditCard(@Valid CardRegistrationDto registrationDto) {
        return Mono.defer(() -> {
           Card creditCard = Card.create(
                   "Credit card",
                   registrationDto.dueDate(),
                   registrationDto.cvc(),
                   registrationDto.ownerName(),
                   registrationDto.ssnOwner()
           );
           return cardRepository.save(creditCard);
        });
    }

    @Override
    public Mono<Card> registerDebitCard(@Valid CardRegistrationDto registrationDto) {
        return Mono.defer(() -> {
            Card debitCard = Card.create(
                    "Debit card",
                    registrationDto.dueDate(),
                    registrationDto.cvc(),
                    registrationDto.ownerName(),
                    registrationDto.ssnOwner()
            );
            return cardRepository.save(debitCard);
        });
    }
}
