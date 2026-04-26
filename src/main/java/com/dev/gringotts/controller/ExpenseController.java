package com.dev.gringotts.controller;

import com.dev.gringotts.dto.ExpenseRequest;
import com.dev.gringotts.dto.ExpenseResponse;
import com.dev.gringotts.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseResponse createExpense(@RequestBody ExpenseRequest expenseRequest) {
        return expenseService.createExpense(expenseRequest);
    }
}
