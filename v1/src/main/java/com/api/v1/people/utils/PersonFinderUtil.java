package com.api.v1.people.utils;

import com.api.v1.db.DbSets;
import com.api.v1.people.domain.Person;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class PersonFinderUtil {

    public Mono<Person> findBySsn(String ssn) {
        return Mono.defer(() -> {
            try {
                Person foundPerson = DbSets
                        .peopleCollection()
                        .whereEqualTo("ssn", ssn)
                        .get()
                        .get()
                        .getDocuments()
                        .get(0)
                        .toObject(Person.class);
                return Mono.just(foundPerson);
            } catch (Exception ignored) {
                return Mono.empty();
            }
        });
    }

    public Mono<Person> findMonoById(String id) {
        return Mono.defer(() -> {
            try {
                Person foundPerson = DbSets
                        .peopleCollection()
                        .document(id)
                        .get()
                        .get()
                        .toObject(Person.class);
                return Mono.just(foundPerson);
            } catch (Exception ignored) {
                return Mono.empty();
            }
        });
    }

    public Person findById(String personId) throws Exception {
        return DbSets
                .peopleCollection()
                .document(personId)
                .get()
                .get()
                .toObject(Person.class);

    }

}
