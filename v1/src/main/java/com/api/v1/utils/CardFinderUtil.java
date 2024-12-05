package com.api.v1.utils;

import com.api.v1.domain.Card;
import com.api.v1.domain.CardRepository;
import com.api.v1.exceptions.NonExistentCardException;
import com.api.v1.annotations.OrderNumber;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CardFinderUtil {

    private final CardRepository cardRepository;

    public CardFinderUtil(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

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
