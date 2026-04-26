package com.dev.gringotts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequest {
    @NotBlank(message = "Amount is required.")
    @Positive(message = "Amount can't be negative.")
    private Double amount;

    private String category;

    @NotNull
    @NotBlank(message = "Description is required.")
    @Size(min = 2, max = 50, message = "Description must be between 2-100 characters.")
    private String description;

    private LocalDate expenseDate;
}
