package edu.uoc.epcsd.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DigitalSessionUnitTest {

    @Test
    void digitalSessionBuilderLinkIsNotNull(){

        Long id = 1L;
        String description = "test description";
        String location = "test location";
        String link = "http://localhost:8080/session3";
        Long userId = 1L;

        DigitalSession digitalSession = DigitalSession.builder()
                .id(id)
                .description(description)
                .location(location)
                .link(link)
                .userId(userId)
                .build();

        assertNotNull(digitalSession);
        assertThat(digitalSession.getLink())
                .isNotNull()
                .isEqualTo(link);
    }

    @Test
    void digitalSessionAllArgConstructorLinkIsNotNull(){
        Long id = 1L;
        String description = "test description";
        String location = "test location";
        String link = "http://localhost:8080/session3";
        Long userId = 1L;

        DigitalSession digitalSession = new DigitalSession(id, description, location, link, userId);

        assertNotNull(digitalSession);
        assertThat(digitalSession.getLink())
                .isNotNull()
                .isEqualTo(link);
    }
}