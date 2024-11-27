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
public class DoctorTerminationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessful() {
        String medicalLicenseNumber = "12345678CA";
        webTestClient
                .patch()
                .uri("api/v1/doctors/%s".formatted(medicalLicenseNumber))
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testUnsuccessful1() {
        String medicalLicenseNumber = "12345678CA";
        webTestClient
                .patch()
                .uri("api/v1/doctors/%s".formatted(medicalLicenseNumber))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    @Order(3)
    void testUnsuccessful2() {
        String medicalLicenseNumber = "12345677CA";
        webTestClient
                .patch()
                .uri("api/v1/doctors/%s".formatted(medicalLicenseNumber))
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
