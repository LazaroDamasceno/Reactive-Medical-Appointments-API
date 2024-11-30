package com.api.v1.cards.utils;

import com.api.v1.cards.domain.Card;
import com.api.v1.cards.dtos.CardResponseDto;
import reactor.core.publisher.Mono;

public class CardResponseMapper {

    public static CardResponseDto mapToDto(Card card) {
        return new CardResponseDto(
                card.type(),
                card.dueDate(),
                card.cvc(),
                card.ownerName(),
                card.OwnerSsn()

        );
    }

    public static Mono<CardResponseDto> mapToMono(Card card) {
        return Mono.just(mapToDto(card));
    }

}
