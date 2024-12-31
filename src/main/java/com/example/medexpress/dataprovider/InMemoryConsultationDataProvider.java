package com.example.medexpress.dataprovider;

import com.example.medexpress.model.Condition;
import com.example.medexpress.model.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryConsultationDataProvider implements ConsultationDataProvider {
    private final List<Condition> conditions = new ArrayList<>();
    private final List<Question> questions = new ArrayList<>();

    public InMemoryConsultationDataProvider() {
        conditions.add(Condition.builder().id(1l).name("Allergy").description("Allergy to Genovian Pear").build());

        questions.add(Question.builder().id(1l).conditionId(1L).text("Are you aged between 18-65 ?").type("boolean").build());
        questions.add(Question.builder().id(2l).conditionId(1L).text("Have you had any adverse reactions to a previously prescribed medicine? ").type("boolean").build());
        questions.add(Question.builder().id(3l).conditionId(1L).text("Have you suffered from any of the following conditions? ").type("single-choice").options(Arrays.asList("Itching", "Rash", "None")).build());
    }

    @Override
    public List<Condition> getConditions() {
        return conditions;
    }

    @Override
    public List<Question> getQuestionsForCondition(Long conditionId) {
        return questions.stream()
                .filter(q -> q.getConditionId().equals(conditionId))
                .collect(Collectors.toList());
    }
}

