package com.api.v1;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRegistrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSuccessful() {
        var registrationDto = new PersonRegistrationDto(
                new FullNameDto(
                        "Lazaro",
                        "",
                        "Santos"
                ),
                LocalDate.parse("2000-12-12"),
                "123456789",
                "leosantos@mail.com",
                new AddressDto(
                        "California",
                        "LA",
                        "Downtown, LA City hall",
                        "90012"
                ),
                "123456789",
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
    void testUnsuccessful1() {
        var registrationDto = new PersonRegistrationDto(
                new FullNameDto(
                        "Lazaro",
                        "",
                        "Santos"
                ),
                LocalDate.parse("2000-12-12"),
                "123456789",
                "leosantos@mail.com",
                new AddressDto(
                        "California",
                        "LA",
                        "Downtown, LA City hall",
                        "90012"
                ),
                "123456789",
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
    void testUnsuccessful2() {
        var registrationDto = new PersonRegistrationDto(
                new FullNameDto(
                        "Lazaro",
                        "",
                        "Santos"
                ),
                LocalDate.parse("2000-12-12"),
                "123456788",
                "leosantos@mail.com",
                new AddressDto(
                        "California",
                        "LA",
                        "Downtown, LA City hall",
                        "90012"
                ),
                "123456789",
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
