package com.api.v1.cards.util;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.domain.CardRepository;
import com.api.v1.cards.exceptions.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CardFinderUtil {

    private final CardRepository cardRepository;

    public Mono<Card> find(String number) {
        return cardRepository
                .findByNumber(new ObjectId(number))
                .hasElement()
                .flatMap(exists -> {
                   if (!exists) {
                       return Mono.error(CardNotFoundException::new);
                   }
                   return cardRepository.findByNumber(new ObjectId(number));
                });
    }

}
