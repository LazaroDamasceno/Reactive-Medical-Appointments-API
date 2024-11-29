package com.api.v1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardDeletionTest {

    @Autowired
    private WebTestClient webTestClient;

    String cardNumber = "";

    @Test
    @Order(1)
    void testCreditCardRegistration() {
        webTestClient
                .delete()
                .uri("api/v1/cards/%s".formatted(cardNumber))
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testDebitCardRegistration() {
        webTestClient
                .delete()
                .uri("api/v1/cards/%s".formatted(cardNumber))
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
