package com.example.medexpress.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {
    private Long conditionId;
    private Long questionId;
    private String answer;
}
