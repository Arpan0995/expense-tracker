package in.arpansharma.expense_tracker_api.controller;

import in.arpansharma.expense_tracker_api.models.Expense;
import in.arpansharma.expense_tracker_api.service.ExpService;
import in.arpansharma.expense_tracker_api.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpService expService;

    public ExpenseController(ExpService expService){
        this.expService = expService;
    }
    @GetMapping("/expenses")
    public List<Expense> fetchAllExpenses(){
        return expService.getExpenses();
    }

    @GetMapping("/expenses/{id}")
    public Expense fetchExpenseById(@PathVariable Long id){
        return expService.getExpenseById(id);
    }

    @DeleteMapping("/delete")
    public void deleteExpense(@RequestParam("id") Long id){
        expService.deleteExpense(id);
    }

    @PostMapping("/addExpense")
    public void addExpense(@RequestBody Expense expense){
        expService.insertExpense(expense);
    }
}
