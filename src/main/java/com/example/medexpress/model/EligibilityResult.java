package com.example.medexpress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EligibilityResult {
    private Long conditionId;
    private boolean isEligible;
    private String message;
}
