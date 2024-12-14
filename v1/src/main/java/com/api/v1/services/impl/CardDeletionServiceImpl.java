package com.api.v1.services.impl;

import com.api.v1.services.cards.CardDeletionService;
import com.api.v1.domain.cards.CardRepository;
import com.api.v1.utils.cards.CardFinderUtil;
import com.api.v1.annotations.MongoDbId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CardDeletionServiceImpl implements CardDeletionService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardFinderUtil cardFinderUtil;

    @Override
    public Mono<Void> deleteByCardNumber(@MongoDbId String cardNumber) {
        return cardFinderUtil
                .findByNumber(cardNumber)
                .flatMap(cardRepository::delete)
                .then();
    }

}
