package com.api.v1.cards.internal.services;

import com.api.v1.cards.CardRetrievalService;
import com.api.v1.cards.Card;
import com.api.v1.cards.CardRepository;
import com.api.v1.cards.CardFinderUtil;
import com.api.v1.people.SSN;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class CardRetrievalServiceImpl implements CardRetrievalService {

    private final CardRepository cardRepository;
    private final CardFinderUtil cardFinderUtil;

    public CardRetrievalServiceImpl(CardRepository cardRepository, CardFinderUtil cardFinderUtil) {
        this.cardRepository = cardRepository;
        this.cardFinderUtil = cardFinderUtil;
    }

    @Override
    public Mono<Card> findByNumber(@SSN String cardNumber) {
        return cardFinderUtil.findByNumber(cardNumber);
    }

    @Override
    public Flux<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Flux<Card> findAllCreditCards() {
        return cardRepository
                .findAll()
                .filter(card -> card.type().equals("Credit card"));
    }

    @Override
    public Flux<Card> findAllDebitCards() {
        return cardRepository
                .findAll()
                .filter(card -> card.type().equals("Debit card"));
    }

}
