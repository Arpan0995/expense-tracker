package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.exception.ResourceNotFoundException;
import in.arpansharma.expense_tracker_api.models.Expense;
import in.arpansharma.expense_tracker_api.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService implements ExpService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Page<Expense> getExpenses(Pageable page){
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense =  expenseRepository.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense does not exist");
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense insertExpense(Expense expense) {
       return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null? expense.getName():existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null?expense.getDescription(): existingExpense.getDescription());
        existingExpense.setAmount(expense.getAmount() != null?expense.getAmount():existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null?expense.getCategory():existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null?expense.getDate():existingExpense.getDate());

        return expenseRepository.save(existingExpense);
    }
}
