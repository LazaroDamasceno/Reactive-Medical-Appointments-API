package com.api.v1.controllers;

import com.api.v1.domain.Card;
import com.api.v1.dtos.CardRegistrationDto;
import com.api.v1.dtos.CardResponseDto;
import com.api.v1.services.CardDeletionService;
import com.api.v1.services.CardRegistrationService;
import com.api.v1.services.CardRetrievalService;
import com.api.v1.annotations.OrderNumber;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/cards")
public class CardController {

    private final CardRegistrationService registrationService;
    private final CardDeletionService deletionService;
    private final CardRetrievalService retrievalService;

    public CardController(
            CardRegistrationService registrationService,
            CardDeletionService deletionService,
            CardRetrievalService retrievalService
    ) {
        this.registrationService = registrationService;
        this.deletionService = deletionService;
        this.retrievalService = retrievalService;
    }

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
