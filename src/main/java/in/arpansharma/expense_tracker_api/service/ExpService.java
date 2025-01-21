package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpService {

    public Page<Expense> getExpenses(Pageable page);

    public Expense getExpenseById(Long id);

    public void deleteExpense(Long id);

    public Expense insertExpense(Expense expense);

    public Expense updateExpense(Long id, Expense expense);
}
