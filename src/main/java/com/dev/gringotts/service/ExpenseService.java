package com.dev.gringotts.service;

import com.dev.gringotts.dto.ExpenseRequest;
import com.dev.gringotts.dto.ExpenseResponse;
import com.dev.gringotts.entity.Expense;
import com.dev.gringotts.exception.ExpenseNotFoundException;
import com.dev.gringotts.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ExpenseResponse getExpenseByExpenseId(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isEmpty()) {
            throw new ExpenseNotFoundException(expenseId);
        }
        return mapToExpenseResponse(expenseOptional.get());
    }

    public List<ExpenseResponse> getAllExpenses() {
        List<Expense> allExpenses = expenseRepository.findAll();
        return allExpenses.stream().map(this::mapToExpenseResponse).toList();
    }

    public ExpenseResponse updateExpense(Long expenseId, ExpenseRequest expenseRequest) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isEmpty()) {
            throw new ExpenseNotFoundException(expenseId);
        }
        Expense expense = expenseOptional.get();
        expense.setExpenseDate(expenseRequest.getExpenseDate());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setDescription(expenseRequest.getDescription());
        expenseRepository.save(expense);
        return mapToExpenseResponse(expense);
    }

    private ExpenseResponse mapToExpenseResponse(Expense savedExpense) {
        return ExpenseResponse.builder().expenseId(savedExpense.getExpenseId())
                .category(savedExpense.getCategory())
                .description(savedExpense.getDescription())
                .creationTime(savedExpense.getCreationTime())
                .expenseDate(savedExpense.getExpenseDate()).build();
    }

    public ExpenseResponse deleteExpense(Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isEmpty()) {
            throw new ExpenseNotFoundException(expenseId);
        }
        expenseRepository.deleteById(expenseId);
        return mapToExpenseResponse(expenseOptional.get());
    }
}
