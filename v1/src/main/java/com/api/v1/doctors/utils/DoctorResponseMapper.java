package com.api.v1.doctors.utils;

import com.api.v1.doctors.domain.Doctor;
import com.api.v1.doctors.dtos.DoctorResponseDto;
import com.api.v1.firestore_db.FirestoreCollections;
import com.api.v1.people.domain.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DoctorResponseMapper {

    public DoctorResponseDto map(Doctor doctor) throws Exception {
        return new DoctorResponseDto(
                FirestoreCollections
                        .peopleCollection()
                        .document(doctor.personId())
                        .get()
                        .get()
                        .toObject(Person.class),
                doctor.licenseNumber(),
                doctor.speciality(),
                doctor.createdAt()

        );
    }

}
