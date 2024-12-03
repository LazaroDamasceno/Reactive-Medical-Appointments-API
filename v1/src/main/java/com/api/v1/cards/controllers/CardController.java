package com.api.v1.cards.controllers;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.dtos.CardRegistrationDto;
import com.api.v1.cards.dtos.CardResponseDto;
import com.api.v1.cards.services.CardDeletionService;
import com.api.v1.cards.services.CardRegistrationService;
import com.api.v1.cards.services.CardRetrievalService;
import com.api.v1.medical_appointments.annotation.OrderNumber;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardRegistrationService registrationService;
    private final CardDeletionService deletionService;
    private final CardRetrievalService retrievalService;

    @PostMapping("credit-card")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<CardResponseDto> registerCreditCard(@Valid @RequestBody CardRegistrationDto registrationDto) {
        return registrationService.registerCreditCard(registrationDto);
    }

    @PostMapping("debit-card")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<CardResponseDto> registerDebitCard(@Valid @RequestBody CardRegistrationDto registrationDto) {
        return registrationService.registerDebitCard(registrationDto);
    }

    @DeleteMapping("{cardNumber}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteByCardNumber(@PathVariable String cardNumber) {
        return deletionService.deleteByCardNumber(cardNumber);
    }

    @GetMapping("{cardNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Card> findByNumber(@OrderNumber @ PathVariable String cardNumber) {
        return retrievalService.findByNumber(cardNumber);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<Card> findAll() {
        return retrievalService.findAll();
    }

    @GetMapping("credit-cards")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<Card> findAllCreditCards() {
        return retrievalService.findAllCreditCards();
    }

    @GetMapping("debit-cards")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<Card> findAllDebitCards() {
        return retrievalService.findAllDebitCards();
    }
}
