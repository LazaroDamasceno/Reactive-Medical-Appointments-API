package com.api.v1.cards.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.domain.CardRepository;
import com.api.v1.cards.dtos.CardRegistrationDto;
import com.api.v1.cards.dtos.CardResponseDto;
import com.api.v1.cards.utils.CardResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CardRegistrationServiceImpl implements CardRegistrationService {

    private final CardRepository cardRepository;

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
