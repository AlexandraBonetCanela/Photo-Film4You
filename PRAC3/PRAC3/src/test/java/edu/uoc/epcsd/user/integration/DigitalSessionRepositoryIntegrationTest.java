package edu.uoc.epcsd.user.integration;

import edu.uoc.epcsd.user.domain.repository.DigitalSessionRepository;
import edu.uoc.epcsd.user.domain.DigitalSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DigitalSessionRepositoryIntegrationTest {

    @Autowired
    private DigitalSessionRepository digitalSessionRepository;

    @Test
    void whenFindDigitalSessionsByUser_thenReturnsDigitalSession() {
        long theUserId = 1L;

        // The database starts empty
        List<DigitalSession> result = digitalSessionRepository.findDigitalSessionByUser(theUserId);
        assertThat(result).isEmpty();

        // Add a digital session for the user we want
        DigitalSession session = DigitalSession.builder()
                .description("test description")
                .link("test link")
                .location("a location")
                .userId(1L)
                .build();
        Long sessionId = digitalSessionRepository.addDigitalSession(session);
        assertThat(sessionId).isNotNull().isNotNegative();

        // Add a digital session for a different user, we should not get this one
        DigitalSession session2 = DigitalSession.builder()
                .description("test2 description")
                .link("test2 link")
                .location("a second location")
                .userId(2L)
                .build();
        Long session2Id = digitalSessionRepository.addDigitalSession(session2);
        assertThat(session2Id).isNotNull().isNotNegative();

        // After the sessions have been created, only the session for the required user should be returned
        result = digitalSessionRepository.findDigitalSessionByUser(theUserId);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserId()).isEqualTo(theUserId);
        assertThat(result.get(0).getId()).isEqualTo(sessionId);
    }
}
