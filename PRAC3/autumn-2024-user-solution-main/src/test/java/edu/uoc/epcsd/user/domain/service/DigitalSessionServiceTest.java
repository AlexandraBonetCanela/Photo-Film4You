package edu.uoc.epcsd.user.domain.service;

import edu.uoc.epcsd.user.application.rest.response.GetUserResponseTest;
import edu.uoc.epcsd.user.domain.repository.DigitalSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import edu.uoc.epcsd.user.domain.DigitalSession;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class DigitalSessionServiceUnitTest {

    @Mock
    private DigitalSessionRepository digitalSessionRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DigitalSessionServiceImpl digitalSessionService;

    @Value("${userService.getUserById.url}")
    private String getUserById;

    @Test
    void findDigitalSessionWithValidId(){
        Long userID = 1L;
        String userEmail = "user@email.com";
        List<DigitalSession> expectedDigitalSessions = List.of(new DigitalSession());

        GetUserResponseTest getUserResponseTest = new GetUserResponseTest();
        getUserResponseTest.setEmail(userEmail);

        Mockito.when(restTemplate.getForEntity(eq(getUserById), eq(GetUserResponseTest.class), eq(userID)))
                .thenReturn(new ResponseEntity<>(getUserResponseTest, HttpStatus.OK));

        Mockito.when(digitalSessionRepository.findDigitalSessionByUser(userID)).thenReturn(expectedDigitalSessions);

        List<DigitalSession> result = digitalSessionService.findDigitalSessionByUser(userID);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expectedDigitalSessions.size());
        assertThat(result).isEqualTo(expectedDigitalSessions);
    }

    @Test
    void findDigitalSessionWithInvalidIdShouldThrowException(){

        Long invalidUserID = 56L;

        Mockito.when(restTemplate.getForEntity(eq(getUserById), eq(GetUserResponseTest.class), eq(invalidUserID)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(UserNotFoundException.class, ()-> {
            digitalSessionService.findDigitalSessionByUser(invalidUserID);
        });
    }
}