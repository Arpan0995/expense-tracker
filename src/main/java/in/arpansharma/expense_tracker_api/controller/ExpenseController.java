package in.arpansharma.expense_tracker_api.controller;

import in.arpansharma.expense_tracker_api.models.Expense;
import in.arpansharma.expense_tracker_api.service.ExpService;
import in.arpansharma.expense_tracker_api.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpService expService;

    public ExpenseController(ExpService expService){
        this.expService = expService;
    }
    @GetMapping("/expenses")
    public List<Expense> fetchAllExpenses(Pageable page){
        return expService.getExpenses(page).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense fetchExpenseById(@PathVariable Long id){
        return expService.getExpenseById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void deleteExpense(@RequestParam("id") Long id){
        expService.deleteExpense(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addExpense")
    public Expense addExpense(@Valid @RequestBody Expense expense){
        return expService.insertExpense(expense);
    }

    @PutMapping("/updateExpense/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id){
        return expService.updateExpense(id, expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getByCategory(@RequestParam String category, Pageable page){
        return expService.getByCategory(category,page).stream().toList();
    }

    @GetMapping("/expenses/name")
    public List<Expense> getByName(@RequestParam String name, Pageable page){
        return expService.getByName(name, page).stream().toList();
    }
}
