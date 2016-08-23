/*package com.crmfollowup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.crmfollowup.domain.User;

public class PersonItemProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public User process(final User person) throws Exception {
        final String nombre = person.getNombre().toUpperCase();
        final String email = person.getEmail().toUpperCase();

        final User transformedPerson = new User(nombre, email);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
*/