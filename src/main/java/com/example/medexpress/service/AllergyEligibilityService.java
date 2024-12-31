package com.example.medexpress.service;

import com.example.medexpress.model.Answer;
import com.example.medexpress.model.EligibilityResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllergyEligibilityService implements EligibilityService {

    @Override
    public EligibilityResult evaluate(Long conditionId, List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getQuestionId() == 1L && "no".equalsIgnoreCase(answer.getAnswer())) {
                return new EligibilityResult(conditionId, false, "Cannot prescribe if age is below 18 or above 65");
            }
            if (answer.getQuestionId() == 2L && "no".equalsIgnoreCase(answer.getAnswer())) {
                return new EligibilityResult(conditionId, false, "Cannot prescribe if there was any allergic reaction previously");
            }
            if (answer.getQuestionId() == 3L && !"None".equalsIgnoreCase(answer.getAnswer())) {
                return new EligibilityResult(conditionId, false, "Cannot prescribe if you had a rash or itching previously");
            }

        }
        return new EligibilityResult(conditionId, true, "You are eligible for a prescription.");
    }
}
