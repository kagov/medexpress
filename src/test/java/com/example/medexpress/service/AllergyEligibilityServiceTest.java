package com.example.medexpress.service;

import com.example.medexpress.model.Answer;
import com.example.medexpress.model.EligibilityResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllergyEligibilityServiceTest {

    private AllergyEligibilityService serviceUnderTest;

    @BeforeEach
    public void setup() {
        serviceUnderTest = new AllergyEligibilityService();
    }

    @Test
    public void testEvaluateReturnsEligibleWhenAllAnswersAreSatisfied() {
        List<Answer> answers = Arrays.asList(
                Answer.builder().questionId(1l).answer("yes").conditionId(1l).build(),
                Answer.builder().questionId(2l).answer("yes").conditionId(1l).build(),
                Answer.builder().questionId(3l).answer("None").conditionId(1l).build());
        EligibilityResult result =  serviceUnderTest.evaluate(1l, answers);
        assertTrue(result.isEligible());
        assertEquals("You are eligible for a prescription.", result.getMessage());
    }

    @Test
    public void testEvaluateReturnsInEligibleWhenAgeIsNotInTheSpecifiedLimit() {
        List<Answer> answers = Arrays.asList(
                Answer.builder().questionId(1l).answer("no").conditionId(1l).build(),
                Answer.builder().questionId(2l).answer("yes").conditionId(1l).build(),
                Answer.builder().questionId(3l).answer("None").conditionId(1l).build());
        EligibilityResult result =  serviceUnderTest.evaluate(1l, answers);
        assertFalse(result.isEligible());
        assertEquals("Cannot prescribe if age is below 18 or above 65", result.getMessage());
    }

    @Test
    public void testEvaluateReturnsInEligibleWhenAllergyIsPresent() {
        List<Answer> answers = Arrays.asList(
                Answer.builder().questionId(1l).answer("yes").conditionId(1l).build(),
                Answer.builder().questionId(2l).answer("no").conditionId(1l).build(),
                Answer.builder().questionId(3l).answer("None").conditionId(1l).build());
        EligibilityResult result =  serviceUnderTest.evaluate(1l, answers);
        assertFalse(result.isEligible());
        assertEquals("Cannot prescribe if there was any allergic reaction previously", result.getMessage());
    }


}
