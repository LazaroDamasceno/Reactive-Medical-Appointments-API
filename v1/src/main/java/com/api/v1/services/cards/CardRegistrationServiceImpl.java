package com.api.v1.services.cards;

import com.api.v1.domain.cards.Card;
import com.api.v1.domain.cards.CardRepository;
import com.api.v1.dtos.cards.CardRegistrationDto;
import com.api.v1.dtos.cards.CardResponseDto;
import com.api.v1.utils.cards.CardResponseMapper;
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
