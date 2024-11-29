package com.api.v1.cards.services;

import com.api.v1.cards.domain.CardRepository;
import com.api.v1.cards.utils.CardFinderUtil;
import com.api.v1.medical_appointments.annotation.OrderNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class CardDeletionServiceImpl implements CardDeletionService {

    private final CardRepository cardRepository;
    private final CardFinderUtil cardFinderUtil;

    @Override
    public Mono<Void> deleteByCardNumber(@OrderNumber String cardNumber) {
        return cardFinderUtil
                .findByNumber(cardNumber)
                .flatMap(cardRepository::delete)
                .then();
    }

}
