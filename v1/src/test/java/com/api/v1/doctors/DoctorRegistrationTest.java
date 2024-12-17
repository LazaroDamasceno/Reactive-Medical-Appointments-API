package com.api.v1.doctors;

import com.api.v1.dtos.doctors.DoctorHiringDto;
import com.api.v1.dtos.people.PersonAddressDto;
import com.api.v1.dtos.people.PersonRegistrationDto;
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
public class DoctorRegistrationTest {

    @Autowired
    private WebTestClient webTestClient;

    DoctorHiringDto registrationDto1 = new DoctorHiringDto(
            "12345678CA",
            new PersonRegistrationDto(
                    "Leonardo",
                    "Silva",
                    "Santos Jr.",
                    LocalDate.parse("2000-12-12"),
                    "987654321",
                    "gabrielsantana@mail.com",
                    new PersonAddressDto(
                            "CA",
                            "LA",
                            "Downtown",
                            "90012"
                    ),
                    "0987654321",
                    "male"
            )
    );

    @Test
    @Order(1)
    void testSuccessful() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto1)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testUnsuccessful1() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto1)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    DoctorHiringDto registrationDto2 = new DoctorHiringDto(
            "12345678CA",
            new PersonRegistrationDto(
                    "Leonardo",
                    "Silva",
                    "Santos Jr.",
                    LocalDate.parse("2000-12-12"),
                    "987654321",
                    "gabrielsantana@mail.com",
                    new PersonAddressDto(
                            "CA",
                            "LA",
                            "Downtown",
                            "90012"
                    ),
                    "0987654321",
                    "male"
            )
    );

    @Test
    @Order(3)
    void testUnsuccessful2() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto2)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    DoctorHiringDto registrationDto3 = new DoctorHiringDto(
            "12345678CA",
            new PersonRegistrationDto(
                    "Leonardo",
                    "Silva",
                    "Santos Jr.",
                    LocalDate.parse("2000-12-12"),
                    "987654320",
                    "gabrielsantana@mail.com",
                    new PersonAddressDto(
                            "CA",
                            "LA",
                            "Downtown",
                            "90012"
                    ),
                    "0987654321",
                    "male"
            )
    );

    @Test
    @Order(4)
    void testUnsuccessful3() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto3)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
