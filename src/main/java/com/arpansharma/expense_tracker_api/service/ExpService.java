package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.dto.ExpenseDTO;
import com.arpansharma.expense_tracker_api.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpService {

    public Page<Expense> getExpenses(Pageable page);

    public Expense getExpenseById(Long id);

    public void deleteExpense(Long id);

    public ExpenseDTO insertExpense(ExpenseDTO expenseDTO);

    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO);

    public List<Expense> getByCategory(String category, Pageable page);

    public List<Expense> getByName(String name, Pageable page);

    public List<Expense> getByDateRange(Date startDate, Date endDate, Pageable page);
}
