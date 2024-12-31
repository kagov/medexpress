package com.example.medexpress.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Condition {
    private Long id;
    private String name;
    private String description;
}
