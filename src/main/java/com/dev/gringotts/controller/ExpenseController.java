package com.dev.gringotts.controller;

import com.dev.gringotts.dto.ExpenseRequest;
import com.dev.gringotts.dto.ExpenseResponse;
import com.dev.gringotts.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest expenseRequest) {
        return new ResponseEntity<>(expenseService.createExpense(expenseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpense(@PathVariable("id") Long expenseId) {
        try {
            return new ResponseEntity<>(expenseService.getExpenseByExpenseId(expenseId), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {
        return new ResponseEntity<>(expenseService.getAllExpenses(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable("id") Long expenseId, @RequestBody ExpenseRequest expenseRequest) {
        try {
            return new ResponseEntity<>(expenseService.updateExpense(expenseId, expenseRequest), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable("id") Long expenseId) {
        try {
            return new ResponseEntity<>(expenseService.deleteExpense(expenseId), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
