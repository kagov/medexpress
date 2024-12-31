package com.example.medexpress.service;

import com.example.medexpress.dataprovider.ConsultationDataProvider;
import com.example.medexpress.dataprovider.InMemoryConsultationDataProvider;
import com.example.medexpress.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsultationServiceTest {

    private ConsultationService serviceUnderTest;

    private ConsultationDataProvider consultationDataProvider = new InMemoryConsultationDataProvider();

    @BeforeEach
    public void setup() {
        serviceUnderTest = new ConsultationService(consultationDataProvider);
    }

    @Test
    public void testGetQuestionsForConditionThatExistsInMemory() {
        List<Question> questions = serviceUnderTest.getQuestionsForCondition(1l);
        assertEquals(3, questions.size(), "There should be 3 questions for this condition");
        assertEquals(1l, questions.get(0).getId(), "The id of the first question should be 1");
        assertEquals("Are you aged between 18-65 ?", questions.get(0).getText(), "Text should equal \"Are you aged between 18-65 ?\"");
    }


    @Test
    public void testGetQuestionsForConditionThatDoesNotExistInMemory() {
        List<Question> questions = serviceUnderTest.getQuestionsForCondition(-1l);
        assertEquals(0, questions.size(), "There should be no questions for this condition");
    }

}
