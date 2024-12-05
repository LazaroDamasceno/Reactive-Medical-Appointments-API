package com.api.v1.medical_appointments;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalAppointmentCancellationTest {

    @Autowired
    private WebTestClient webTestClient;

    String orderNumber = "";

    @Test
    @Order(1)
    void testSuccessful() {
        webTestClient
                .patch()
                .uri("api/v1/medical-appointments/%s/cancellation".formatted(orderNumber))
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testUnSuccessfulForImmutableMedicalAppointment() {
        webTestClient
                .patch()
                .uri("api/v1/medical-appointments/%s/cancellation".formatted(orderNumber))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    @Order(3)
    void testUnSuccessfulForNotFoundMedicalAppointment() {
        webTestClient
                .patch()
                .uri("api/v1/medical-appointments/%s/cancellation".formatted("123456789012345678901234"))
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
