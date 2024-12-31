package com.example.medexpress.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MedexpressAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetQuestionsForCondition() throws Exception {
        mockMvc.perform(get("/api/consultation/1/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void testSubmitAnswersReturnsEligibleForValidAnswers() throws Exception {
        mockMvc.perform(post("/api/consultation/1/answers")
                        .content(getValidAnswer())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eligible").value(true))
                .andExpect(jsonPath("$.message").value("You are eligible for a prescription."));
    }

    @Test
    public void testSubmitAnswersReturnsInEligibleWhenAgeIsOutOfBounds() throws Exception {
        mockMvc.perform(post("/api/consultation/1/answers")
                        .content(getAnswersWithOutOfBoundsAge())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eligible").value(false))
                .andExpect(jsonPath("$.message").value("Cannot prescribe if age is below 18 or above 65"));
    }



    private String getValidAnswer() {
        return "[\n  {\n    \"questionId\": 1,\n    \"answer\": \"yes\"\n  },\n{\n  " +
                "  \"questionId\": 2,\n    \"answer\": \"yes\"\n  },\n{\n    \"questionId\": 3,\n    \"answer\": \"None\"\n  }\n]";
    }

    private String getAnswersWithOutOfBoundsAge() {
        return "[\n  {\n    \"questionId\": 1,\n    \"answer\": \"no\"\n  },\n{\n  " +
                "  \"questionId\": 2,\n    \"answer\": \"yes\"\n  },\n{\n    \"questionId\": 3,\n    \"answer\": \"None\"\n  }\n]";
    }

}
