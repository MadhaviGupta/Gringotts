package com.dev.gringotts.repository;

import com.dev.gringotts.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findByExpenseId(Long expenseId);

    List<Expense> findByUserId(Long userId);

    // Todo
    Expense findByUserIdAndExpenseId(Long expenseId, Long userId);
}
