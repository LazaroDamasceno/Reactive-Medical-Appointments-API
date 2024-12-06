package com.api.v1.cards;

import com.api.v1.dtos.cards.CardRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardDebitRegistrationTest {

    @Autowired
    private WebTestClient webTestClient;

    CardRegistrationDto registrationDto = new CardRegistrationDto(
            LocalDate.parse("2025-12-12"),
            "123",
            "Leonardo Santos",
            "123456789"
    );

    @Test
    void testDebitCardRegistration() {
        webTestClient
                .post()
                .uri("api/v1/cards/debit-card")
                .bodyValue(registrationDto)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

}
