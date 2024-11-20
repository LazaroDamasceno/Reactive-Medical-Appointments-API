package com.api.v1;

import com.api.v1.people.dtos.PersonAddressDto;
import com.api.v1.people.dtos.PersonFullNameDto;
import com.api.v1.people.dtos.PersonModificationDto;
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
public class CustomerModificationTest {

    @Autowired
    private WebTestClient webTestClient;

    PersonModificationDto modificationDto = new PersonModificationDto(
            new PersonFullNameDto(
                    "Leonardo",
                    "Silva",
                    "Santos Jr."
            ),
            LocalDate.parse("2004-12-12"),
            "jr@leosantos.com",
            new PersonAddressDto(
                    "CO",
                    "Denver",
                    "Downtown",
                    "90012"
            ),
            "1234567890",
            "cis male"
    );

    @Test
    @Order(1)
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
    @Order(2)
    void testUnsuccessful() {
        String ssn = "123456779";
        webTestClient
                .put()
                .uri("api/v1/customers/%s".formatted(ssn))
                .bodyValue(modificationDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
