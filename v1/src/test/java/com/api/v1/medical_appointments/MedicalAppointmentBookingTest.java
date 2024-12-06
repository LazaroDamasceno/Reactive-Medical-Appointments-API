package com.api.v1.medical_appointments;

import com.api.v1.dtos.medical_appointments.MedicalAppointmentBookingDto;
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
public class MedicalAppointmentBookingTest {

    @Autowired
    private WebTestClient webTestClient;

    MedicalAppointmentBookingDto bookingDto = new MedicalAppointmentBookingDto(
            "123456789",
            "12345678CA",
            LocalDateTime.parse("2024-12-12T00:00:00")
    );

    @Test
    @Order(1)
    void testSuccessful() {
        webTestClient
                .post()
                .uri("api/v1/medical-appointments/paid")
                .bodyValue(bookingDto)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testUnsuccessfulForDuplicatedBookingDate() {
        webTestClient
                .post()
                .uri("api/v1/medical-appointments/paid")
                .bodyValue(bookingDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    MedicalAppointmentBookingDto bookingDto2 = new MedicalAppointmentBookingDto(
            "123456788",
            "12345678CA",
            LocalDateTime.parse("2024-12-12T00:00:00")
    );

    @Test
    @Order(3)
    void testUnsuccessfulForNotFoundCustomer() {
        webTestClient
                .post()
                .uri("api/v1/medical-appointments/paid")
                .bodyValue(bookingDto2)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    MedicalAppointmentBookingDto bookingDto3 = new MedicalAppointmentBookingDto(
            "123456789",
            "12345677CA",
            LocalDateTime.parse("2024-12-12T00:00:00")
    );

    @Test
    @Order(4)
    void testUnsuccessfulForNotFoundDoctor() {
        webTestClient
                .post()
                .uri("api/v1/medical-appointments/paid")
                .bodyValue(bookingDto3)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
