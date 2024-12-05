package com.api.v1.utils;

import com.api.v1.domain.Card;
import com.api.v1.dtos.CardResponseDto;
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
