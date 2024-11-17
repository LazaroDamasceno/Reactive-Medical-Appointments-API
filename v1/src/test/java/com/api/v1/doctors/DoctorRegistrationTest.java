package com.api.v1.doctors;

import com.api.v1.doctors.dtos.DoctorLicenseNumberDto;
import com.api.v1.doctors.dtos.DoctorRegistrationDto;
import com.api.v1.people.dtos.AddressDto;
import com.api.v1.people.dtos.FullNameDto;
import com.api.v1.people.dtos.PersonRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorRegistrationTest {

    @Autowired
    private WebTestClient webTestClient;

    DoctorRegistrationDto registrationDto1 = new DoctorRegistrationDto(
            new DoctorLicenseNumberDto(
                    "12345678",
                    "CA"
            ),
            new PersonRegistrationDto(
                    new FullNameDto(
                            "Gabriel",
                            "",
                            "Santana"
                    ),
                    LocalDate.parse("2000-12-12"),
                    "987654321",
                    "gabrielsantana@mail.com",
                    new AddressDto(
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
    void testSuccessful() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto1)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    @Test
    void testUnsuccessful1() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto1)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    DoctorRegistrationDto registrationDto2 = new DoctorRegistrationDto(
            new DoctorLicenseNumberDto(
                    "12345677",
                    "CA"
            ),
            new PersonRegistrationDto(
                    new FullNameDto(
                            "Gabriel",
                            "",
                            "Santana"
                    ),
                    LocalDate.parse("2000-12-12"),
                    "987654321",
                    "gabrielsantana@mail.com",
                    new AddressDto(
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
    void testUnsuccessful2() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto2)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    DoctorRegistrationDto registrationDto3 = new DoctorRegistrationDto(
            new DoctorLicenseNumberDto(
                    "12345677",
                    "CA"
            ),
            new PersonRegistrationDto(
                    new FullNameDto(
                            "Gabriel",
                            "",
                            "Santana"
                    ),
                    LocalDate.parse("2000-12-12"),
                    "987654320",
                    "gabrielsantana@mail.com",
                    new AddressDto(
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
    void testUnsuccessful3() {
        webTestClient
                .post()
                .uri("api/v1/doctors")
                .bodyValue(registrationDto3)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
