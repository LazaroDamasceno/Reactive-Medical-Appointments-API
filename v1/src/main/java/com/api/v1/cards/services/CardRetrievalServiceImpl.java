package com.api.v1.cards.services;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.domain.CardRepository;
import com.api.v1.cards.utils.CardFinderUtil;
import com.api.v1.people.annotations.SSN;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CardRetrievalServiceImpl implements CardRetrievalService {

    private final CardRepository cardRepository;
    private final CardFinderUtil cardFinderUtil;

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
