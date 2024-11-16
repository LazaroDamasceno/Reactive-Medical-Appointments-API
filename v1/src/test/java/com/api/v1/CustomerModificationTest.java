package com.api.v1;

import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import com.api.v1.people.dtos.PersonModificationDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerModificationTest {

    @Autowired
    private WebTestClient webTestClient;

    PersonModificationDto modificationDto;

    @BeforeEach
    void setUp() {
        modificationDto = new PersonModificationDto(
                new FullNameDto(
                        "Leonard",
                        "Silva",
                        "Santos Jr."
                ),
                LocalDate.parse("2004-12-12"),
                "jr@leosantos.com",
                new AddressDto(
                        "Colorado",
                        "Denver",
                        "Downtown, Denver City hall",
                        "90012"
                ),
                "0987654321",
                "cis male"
        );
    }

    @Test
    void testSuccessful() {
        String ssn = "123456789";
        webTestClient
                .put()
                .uri("api/v1/customers/%s".formatted(ssn))
                .bodyValue(modificationDto)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void testUnsuccessful() {
        String ssn = "123456788";
        webTestClient
                .put()
                .uri("api/v1/customers/%s".formatted(ssn))
                .bodyValue(modificationDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
