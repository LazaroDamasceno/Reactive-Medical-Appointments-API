package com.api.v1.doctors;

import com.api.v1.people.PersonAddressDto;
import com.api.v1.people.PersonModificationDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorModificationTest {

    @Autowired
    private WebTestClient webTestClient;

    PersonModificationDto modificationDto = new PersonModificationDto(
            "Gabriel",
            "Silva",
            "Santana Jr.",
            LocalDate.parse("2003-12-12"),
            "jr@gsantana.io",
            new PersonAddressDto(
                    "CA",
                    "Sant'ana",
                    "Downtown",
                    "90012"
            ),
            "0987654321",
            "cis male"
    );

    @Test
    @Order(1)
    void testSuccessful() {
        String licenseNumber = "12345678CA";
        webTestClient
                .put()
                .uri("api/v1/doctors/%s".formatted(licenseNumber))
                .bodyValue(modificationDto)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testUnsuccessful() {
        String licenseNumber = "12345677CA";
        webTestClient
                .put()
                .uri("api/v1/doctors/%s".formatted(licenseNumber))
                .bodyValue(modificationDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
