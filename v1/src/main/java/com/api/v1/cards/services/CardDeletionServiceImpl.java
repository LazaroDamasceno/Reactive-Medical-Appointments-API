package com.api.v1.cards.services;

import com.api.v1.cards.CardDeletionService;
import com.api.v1.cards.CardRepository;
import com.api.v1.cards.CardFinderUtil;
import com.api.v1.medical_appointments.OrderNumber;
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
    public Mono<Void> deleteByCardNumber(@OrderNumber String cardNumber) {
        return cardFinderUtil
                .findByNumber(cardNumber)
                .flatMap(cardRepository::delete)
                .then();
    }

}
