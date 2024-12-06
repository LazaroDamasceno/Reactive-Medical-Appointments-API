package com.api.v1.payments;

import com.api.v1.dtos.medical_appointments.MedicalAppointmentPaymentDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentMedicalPaymentTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    public void testSuccessful() {
        var paymentDto = new MedicalAppointmentPaymentDto(
                "",
                "",
                80.0
        );
        webTestClient.post()
                .uri("api/v1/payments")
                .bodyValue(paymentDto)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    public void testUnsuccessful() {
        var paymentDto = new MedicalAppointmentPaymentDto(
                "",
                "",
                80.0
        );
        webTestClient.post()
                .uri("api/v1/payments")
                .bodyValue(paymentDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    @Order(3)
    public void testUnsuccessful2() {
        var paymentDto = new MedicalAppointmentPaymentDto(
                "",
                "",
                80.0
        );
        webTestClient.post()
                .uri("api/v1/payments")
                .bodyValue(paymentDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
