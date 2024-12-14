package com.api.v1.medical_slots;

import com.api.v1.dtos.medical_slots.MedicalSlotRegistrationDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalSlotRegistrationTest {

    @Autowired
    private WebTestClient webTestClient;

    MedicalSlotRegistrationDto registrationDto = new MedicalSlotRegistrationDto(
            "12345678CA",
            LocalDateTime.parse("2024-12-12T12:30:30")
    );

    @Order(1)
    @Test
    void testSuccessful() {
        webTestClient
                .post()
                .uri("api/v1/medical-slots")
                .bodyValue(registrationDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Order(2)
    @Test
    void testUnsuccessful() {
        webTestClient
                .post()
                .uri("api/v1/medical-slots")
                .bodyValue(registrationDto)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    MedicalSlotRegistrationDto registrationDto2 = new MedicalSlotRegistrationDto(
            "12345677CA",
            LocalDateTime.parse("2024-12-12T12:30:30")
    );

    @Order(3)
    @Test
    void testUnsuccessful2() {
        webTestClient
                .post()
                .uri("api/v1/medical-slots")
                .bodyValue(registrationDto2)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

}
