package com.example.medexpress.service;

import com.example.medexpress.dataprovider.ConsultationDataProvider;
import com.example.medexpress.model.Condition;
import com.example.medexpress.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationDataProvider dataProvider;


    public List<Condition> getConditions() {
        return dataProvider.getConditions();
    }

    public List<Question> getQuestionsForCondition(Long conditionId) {
        return dataProvider.getQuestionsForCondition(conditionId);
    }
}

