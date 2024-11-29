package com.api.v1.cards.utils;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.domain.CardRepository;
import com.api.v1.cards.exceptions.NonExistentCardException;
import com.api.v1.medical_appointments.annotation.OrderNumber;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CardFinderUtil {

    private final CardRepository cardRepository;

    public Mono<Card> findByNumber(@OrderNumber String number) {
        return cardRepository
                .findByNumber(new ObjectId(number))
                .singleOptional()
                .flatMap(optional -> {
                   if (optional.isEmpty()) {
                       return Mono.error(NonExistentCardException::new);
                   }
                   return Mono.just(optional.get());
                });
    }

}
