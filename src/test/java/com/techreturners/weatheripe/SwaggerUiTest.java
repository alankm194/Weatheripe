package com.techreturners.weatheripe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SwaggerUiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSwaggerUIisAccessible() throws Exception {
        mockMvc.perform(get("/swagger-ui"))
                .andExpect(redirectedUrl("/swagger-ui/index.html"))
                .andExpect(status().isFound());

        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk());

    }

}