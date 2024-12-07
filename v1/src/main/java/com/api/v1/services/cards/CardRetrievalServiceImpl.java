package com.api.v1.services.cards;

import com.api.v1.domain.cards.Card;
import com.api.v1.domain.cards.CardRepository;
import com.api.v1.utils.cards.CardFinderUtil;
import com.api.v1.annotations.SSN;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CardRetrievalServiceImpl implements CardRetrievalService {

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
