package com.api.v1.doctors.utils;

import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.firestore_db.FirestoreCollections;
import com.api.v1.people.domain.Person;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class DoctorResponseMapper {

    public DoctorResponseDto mapToDto(Doctor doctor) throws Exception {
        return new DoctorResponseDto(
                FirestoreCollections
                        .peopleCollection()
                        .document(doctor.getPersonId())
                        .get()
                        .get()
                        .toObject(Person.class),
                doctor.getLicenseNumber(),
                doctor.getSpeciality(),
                doctor.getCreatedAt()
        );
    }

    public Mono<DoctorResponseDto> mapToMono(Doctor doctor) throws Exception {
        return Mono.just(mapToDto(doctor));
    }

}
