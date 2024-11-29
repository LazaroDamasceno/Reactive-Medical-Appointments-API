package com.api.v1.cards.controllers;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.dtos.CardRegistrationDto;
import com.api.v1.cards.services.CardDeletionService;
import com.api.v1.cards.services.CardRegistrationService;
import com.api.v1.cards.services.CardRetrievalService;
import com.api.v1.medical_appointments.annotation.OrderNumber;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public Mono<Card> registerCreditCard(@Valid @RequestBody CardRegistrationDto registrationDto) {
        return registrationService.registerCreditCard(registrationDto);
    }

    @PostMapping("debit-card")
    public Mono<Card> registerDebitCard(@Valid @RequestBody CardRegistrationDto registrationDto) {
        return registrationService.registerDebitCard(registrationDto);
    }

    @DeleteMapping("{cardNumber}")
    public Mono<Void> deleteByCardNumber(@PathVariable String cardNumber) {
        return deletionService.deleteByCardNumber(cardNumber);
    }

    @GetMapping("{cardNumber}")
    public Mono<Card> findByNumber(@OrderNumber @ PathVariable String cardNumber) {
        return retrievalService.findByNumber(cardNumber);
    }

    @GetMapping
    public Flux<Card> findAll() {
        return retrievalService.findAll();
    }

    @GetMapping("credit-cards")
    public Flux<Card> findAllCreditCards() {
        return retrievalService.findAllCreditCards();
    }

    @GetMapping("debit-cards")
    public Flux<Card> findAllDebitCards() {
        return retrievalService.findAllDebitCards();
    }
}
