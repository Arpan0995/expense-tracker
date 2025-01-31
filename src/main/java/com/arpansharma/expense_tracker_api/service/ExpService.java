package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.dto.ExpenseDTO;
import com.arpansharma.expense_tracker_api.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpService {

    public List<ExpenseDTO> getExpenses(Pageable page);

    public ExpenseDTO getExpenseById(String expenseId);

    public void deleteExpense(String expenseId);

    public ExpenseDTO insertExpense(ExpenseDTO expenseDTO);

    public ExpenseDTO updateExpense(String expenseId, ExpenseDTO expenseDTO);

    public List<ExpenseDTO> getByCategory(String categoryId, Pageable page);

    public List<ExpenseDTO> getByName(String name, Pageable page);

    public List<ExpenseDTO> getByDateRange(Date startDate, Date endDate, Pageable page);
}
