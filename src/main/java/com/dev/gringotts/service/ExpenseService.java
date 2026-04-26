package com.dev.gringotts.service;

import com.dev.gringotts.dto.ExpenseRequest;
import com.dev.gringotts.dto.ExpenseResponse;
import com.dev.gringotts.entity.Expense;
import com.dev.gringotts.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponse createExpense(ExpenseRequest expenseRequest) {
        Expense expense = new Expense(expenseRequest.getAmount(), expenseRequest.getCategory(), expenseRequest.getDescription(), expenseRequest.getExpenseDate());
        Expense savedExpense = expenseRepository.save(expense);
        return mapToExpenseResponse(savedExpense);
    }

    private ExpenseResponse mapToExpenseResponse(Expense savedExpense) {
        return ExpenseResponse.builder().expenseId(savedExpense.getExpenseId())
                .category(savedExpense.getCategory())
                .description(savedExpense.getDescription())
                .creationTime(savedExpense.getCreationTime())
                .expenseDate(savedExpense.getExpenseDate()).build();
    }

    public Expense getExpenseByExpenseId(Long expenseId) {
        return expenseRepository.findByExpenseId(expenseId);
    }

    public List<Expense> getAllExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }
}
