package com.dev.gringotts.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class ExpenseResponse {

    private Long expenseId;
    private String category;
    private String description;
    private LocalDateTime creationTime;
    private LocalDate expenseDate;
}
