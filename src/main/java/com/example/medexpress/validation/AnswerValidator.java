package com.example.medexpress.validation;

import com.example.medexpress.exception.InvalidQuestionException;
import com.example.medexpress.model.Answer;
import com.example.medexpress.model.Question;
import com.example.medexpress.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is a simple implementation of a validator. It can extend from an abstract class that defines common validations like
 * validating text input, validating boolean etc.
 * If the validation fails then an exception will be thrown.
 */
@Service
@RequiredArgsConstructor
public class AnswerValidator {

    private final ConsultationService consultationService;

    public void validate(Long conditionId, List<Answer> answers) {
        List<Question> questions = consultationService.getQuestionsForCondition(conditionId);
        List<Long> questionIds = questions.stream().map(Question::getId).toList();
        for (Answer answer : answers) {
            if (!questionIds.contains(answer.getQuestionId())) {
                throw new InvalidQuestionException(String.format("Question id %d is invalid for the answer", answer.getQuestionId()));
            }
        }
        // check if all questions are answered
        // validate each answer based on the type of question
        // for example for a yes or no based question check if the answer was either yes or no
    }
}
