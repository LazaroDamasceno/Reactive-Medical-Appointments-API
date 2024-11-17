package com.api.v1.doctors;

import com.api.v1.doctors.dtos.DoctorLicenseNumberDto;
import com.api.v1.doctors.dtos.DoctorModificationDto;
import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
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
public class DoctorModificationTest {

    @Autowired
    private WebTestClient webTestClient;

    DoctorModificationDto modificationDto1 = new DoctorModificationDto(
            new DoctorLicenseNumberDto(
                    "12345678",
                    "CA"
            ),
            new PersonModificationDto(
                    new FullNameDto(
                            "Gabriel",
                            "Silva",
                            "Santana Jr."
                    ),
                    LocalDate.parse("2000-12-12"),
                    "Santana",
                    new AddressDto("TN", "Nashville", "Downtown", "90012"),
                    "1234567890",
                    "cis male"
            )
    );

    @Test
    @Order(1)
    void testSuccessful() {
        webTestClient
                .put()
                .uri("api/v1/doctors")
                .bodyValue(modificationDto1)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    DoctorModificationDto modificationDto2 = new DoctorModificationDto(
            new DoctorLicenseNumberDto(
                    "12345678",
                    "CA"
            ),
            new PersonModificationDto(
                    new FullNameDto(
                            "Gabriel",
                            "Silva",
                            "Santana Jr."
                    ),
                    LocalDate.parse("2000-12-12"),
                    "Santana",
                    new AddressDto("TN", "Nashville", "Downtown", "90012"),
                    "1234567890",
                    "cis male"
            )
    );

    @Test
    @Order(2)
    void testUnsuccessful() {
        webTestClient
                .put()
                .uri("api/v1/doctors")
                .bodyValue(modificationDto2)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
