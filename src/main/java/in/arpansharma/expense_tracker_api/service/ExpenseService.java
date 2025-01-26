package in.arpansharma.expense_tracker_api.service;

import in.arpansharma.expense_tracker_api.exception.ResourceNotFoundException;
import in.arpansharma.expense_tracker_api.models.Expense;
import in.arpansharma.expense_tracker_api.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService implements ExpService {

    private final ExpenseRepository expenseRepository;

    private final UserService userService;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserService userService){
        this.expenseRepository = expenseRepository;
        this.userService = userService;
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
        getExpenseById(id);
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense insertExpense(Expense expense) {
        expense.setUser(userService.getLoggedInuser());
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

    @Override
    public List<Expense> getByCategory(String category, Pageable page) {
        return expenseRepository.findByCategory(category,page).toList();
    }

    @Override
    public List<Expense> getByName(String name, Pageable page) {
        return expenseRepository.findByNameContaining(name,page).toList();
    }

    @Override
    public List<Expense> getByDateRange(@RequestParam Date startDate,@RequestParam Date endDate, Pageable page) {
        if(startDate == null){
            startDate = new Date(0);
        }
        if(endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByDateBetween(startDate, endDate, page).toList();
    }
}
