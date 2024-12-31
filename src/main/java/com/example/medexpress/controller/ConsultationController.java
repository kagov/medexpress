package com.example.medexpress.controller;


import com.example.medexpress.exception.InvalidQuestionException;
import com.example.medexpress.model.Answer;
import com.example.medexpress.model.Condition;
import com.example.medexpress.model.EligibilityResult;
import com.example.medexpress.model.Question;
import com.example.medexpress.service.ConsultationService;
import com.example.medexpress.service.EligibilityEvaluatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;
    private final EligibilityEvaluatorService eligibilityEvaluatorService;

    @GetMapping("/conditions")
    public List<Condition> getConditions() {
        return consultationService.getConditions();
    }

    @GetMapping("/{conditionId}/questions")
    public List<Question> getQuestionsForCondition(@PathVariable Long conditionId) {
        return consultationService.getQuestionsForCondition(conditionId);
    }

    @PostMapping("/{conditionId}/answers")
    public EligibilityResult submitAnswers(@PathVariable Long conditionId, @RequestBody List<Answer> answers) {
        return eligibilityEvaluatorService.evaluateEligibility(conditionId, answers);
    }

    @ExceptionHandler(InvalidQuestionException.class)
    public ResponseEntity<String> handleCacheNotFoundException(InvalidQuestionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

