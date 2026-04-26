package com.dev.gringotts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private Double amount;
    private String category;
    private String description;
    private LocalDateTime creationTime;
    private LocalDate expenseDate;
    private Long userId;

    public Expense(Double amount, String category, String description, LocalDate expenseDate) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.expenseDate = expenseDate;
    }

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
    }
}
