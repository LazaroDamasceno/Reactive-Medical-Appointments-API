package com.api.v1.customers;

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
public class CustomerRegistrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void testSuccessful() {
        var registrationDto = new PersonRegistrationDto(
                "Lazaro",
                "",
                "Santos",
                LocalDate.parse("2000-12-12"),
                "123456789",
                "leosantos@mail.com",
                new PersonAddressDto(
                        "CA",
                        "LA",
                        "Downtown, LA City hall",
                        "90012"
                ),
                "1234567890",
                "male"
        );
        webTestClient
                .post()
                .uri("api/v1/customers")
                .bodyValue(registrationDto)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    @Order(2)
    void testUnsuccessful1() {
        var registrationDto = new PersonRegistrationDto(
                "Lazaro",
                "",
                "Santos",
                LocalDate.parse("2000-12-12"),
                "123456789",
                "leosantos@mail.com",
                new PersonAddressDto(
                        "CA",
                        "LA",
                        "Downtown, LA City hall",
                        "90012"
                ),
                "1234567890",
                "male"

        );
        webTestClient
                .post()
                .uri("api/v1/customers")
                .bodyValue(registrationDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    @Order(3)
    void testUnsuccessful2() {
        var registrationDto = new PersonRegistrationDto(
                "Lazaro",
                "",
                "Santos",
                LocalDate.parse("2000-12-12"),
                "123456788",
                "leosantos@mail.com",
                new PersonAddressDto(
                        "CA",
                        "LA",
                        "Downtown, LA City hall",
                        "90012"
                ),
                "1234567890",
                "male"

        );
        webTestClient
                .post()
                .uri("api/v1/customers")
                .bodyValue(registrationDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
