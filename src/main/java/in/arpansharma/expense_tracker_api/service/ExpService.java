package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.models.Expense;

import java.util.List;

public interface ExpService {

    public List<Expense> getExpenses();

    public Expense getExpenseById(Long id);

    public void deleteExpense(Long id);

    public Expense insertExpense(Expense expense);

    public Expense updateExpense(Long id, Expense expense);
}
