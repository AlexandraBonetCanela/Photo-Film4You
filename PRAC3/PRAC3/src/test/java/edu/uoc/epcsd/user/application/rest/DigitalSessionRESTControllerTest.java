package edu.uoc.epcsd.user.application.rest;

import edu.uoc.epcsd.user.domain.DigitalSession;
import edu.uoc.epcsd.user.domain.service.DigitalSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(DigitalSessionRESTController.class)
class DigitalSessionRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DigitalSessionService digitalSessionService;

    private List<DigitalSession> mockDigitalSession;

    @BeforeEach
    void setUp(){
        DigitalSession session = DigitalSession.builder()
                .id(1L)
                .description("test description")
                .link("test link")
                .userId(1L)
                .build();

        mockDigitalSession = List.of(session);
    }

    @Test
    void getDigitalSessionByUser () throws Exception{

        Mockito.when(digitalSessionService.findDigitalSessionByUser(anyLong()))
                .thenReturn(mockDigitalSession);

        mockMvc.perform(get("/digital/digitalByUser")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].link", is("test link")));
    }
}