package com.api.v1.medical_slots;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.atomic.AtomicReference;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalSlotCompletionTest {

    @Autowired
    private WebTestClient webTestClient;

    private final AtomicReference<String> id = new AtomicReference<>("");

    @Order(1)
    @Test
    void testSuccessful() {
        webTestClient
                .patch()
                .uri("api/v1/medical-slots/%s/completion".formatted(id.get()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Order(2)
    @Test
    void testUnsuccessful() {
        id.set("");
        webTestClient
                .patch()
                .uri("api/v1/medical-slots/%s/completion".formatted(id.get()))
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}
