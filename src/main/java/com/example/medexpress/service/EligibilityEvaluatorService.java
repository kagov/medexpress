package com.example.medexpress.service;

import com.example.medexpress.factory.EligibilityServiceFactory;
import com.example.medexpress.model.Answer;
import com.example.medexpress.model.EligibilityResult;
import com.example.medexpress.validation.AnswerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EligibilityEvaluatorService {

    private final EligibilityServiceFactory eligibilityServiceFactory;
    private final AnswerValidator answerValidator;

    public EligibilityResult evaluateEligibility(Long conditionId, List<Answer> answers) {
        answerValidator.validate(conditionId, answers);
        return eligibilityServiceFactory.getService(conditionId).evaluate(conditionId, answers);
    }
}
