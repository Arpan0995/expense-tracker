package com.arpansharma.expense_tracker_api.repository;

import com.arpansharma.expense_tracker_api.models.Category;
import com.arpansharma.expense_tracker_api.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public Page<Expense> findByUserIdAndCategory(Long userId, Category category, Pageable page);

    public Page<Expense> findByUserIdAndNameContaining(Long userId, String name, Pageable page);

    public Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

    public Page<Expense> findByUserId(Long user_id, Pageable page);

    public Optional<Expense> findByUserIdAndExpenseId(Long user_id, String expenseId);

}
