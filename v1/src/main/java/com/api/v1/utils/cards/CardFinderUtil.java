package com.api.v1.utils.cards;

import com.api.v1.domain.cards.Card;
import com.api.v1.domain.cards.CardRepository;
import com.api.v1.exceptions.cards.NonExistentCardException;
import com.api.v1.annotations.MongoDbId;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CardFinderUtil {

    private final CardRepository cardRepository;

    public CardFinderUtil(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Mono<Card> findByNumber(@MongoDbId String number) {
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
