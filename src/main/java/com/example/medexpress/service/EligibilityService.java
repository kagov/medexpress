package com.example.medexpress.service;

import com.example.medexpress.model.Answer;
import com.example.medexpress.model.EligibilityResult;

import java.util.List;

public interface EligibilityService {
    EligibilityResult evaluate(Long conditionId , List<Answer> answers);
}
