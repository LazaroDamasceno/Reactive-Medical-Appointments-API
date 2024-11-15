package com.api.v1.people.util;

import com.api.v1.people.domain.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonFullNameUtil {

    public String getFullName(Person person) {
        if (person.getMiddleName() == null || person.getMiddleName().isEmpty()) {
            return String.format("%s %s", person.getFirstName(), person.getLastName());
        }
        return "%s %s %s".formatted(person.getFirstName(), person.getMiddleName(), person.getLastName());
    }

}
