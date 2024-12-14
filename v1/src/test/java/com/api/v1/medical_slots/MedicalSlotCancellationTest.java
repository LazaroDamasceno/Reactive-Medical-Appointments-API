package com.api.v1.medical_slots;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalSlotCancellationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Order(1)
    @Test
    void testSuccessful() {
        String id = "";
        webTestClient
                .patch()
                .uri("api/v1/medical-slots/%s".formatted(id))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Order(2)
    @Test
    void testUnsuccessful() {
        String id = "";
        webTestClient
                .patch()
                .uri("api/v1/medical-slots/%s".formatted(id))
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}
