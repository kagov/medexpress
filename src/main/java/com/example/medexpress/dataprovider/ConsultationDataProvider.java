package com.example.medexpress.dataprovider;

import com.example.medexpress.model.Condition;
import com.example.medexpress.model.Question;

import java.util.List;

public interface ConsultationDataProvider {
    List<Condition> getConditions();
    List<Question> getQuestionsForCondition(Long conditionId);
}
