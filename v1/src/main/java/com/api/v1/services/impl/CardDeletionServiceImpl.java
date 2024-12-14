package com.api.v1.services.impl;

import com.api.v1.services.cards.CardDeletionService;
import com.api.v1.domain.cards.CardRepository;
import com.api.v1.utils.cards.CardFinderUtil;
import com.api.v1.annotations.MongoObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CardDeletionServiceImpl implements CardDeletionService {

    private final CardRepository cardRepository;
    private final CardFinderUtil cardFinderUtil;

    public CardDeletionServiceImpl(CardRepository cardRepository, CardFinderUtil cardFinderUtil) {
        this.cardRepository = cardRepository;
        this.cardFinderUtil = cardFinderUtil;
    }

    @Override
    public Mono<Void> deleteByCardNumber(@MongoObjectId String cardNumber) {
        return cardFinderUtil
                .findByNumber(cardNumber)
                .flatMap(cardRepository::delete)
                .then();
    }

}
