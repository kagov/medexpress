package com.example.medexpress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Question {
    private Long id;
    private Long conditionId;
    private String text;
    // can be enriched with an enum
    private String type;
    private List<String> options;
}
