package in.arpansharma.expense_tracker_api.controller;

import in.arpansharma.expense_tracker_api.models.Expense;
import in.arpansharma.expense_tracker_api.service.ExpService;
import in.arpansharma.expense_tracker_api.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "My Swagger", description = "How to use Expense APIs")
public class ExpenseController {

    private final ExpService expService;

    public ExpenseController(ExpService expService){
        this.expService = expService;
    }

    @Operation(summary = "All Expenses")
    @GetMapping("/allExpenses")
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

    @GetMapping("/expenses/date")
    public List<Expense> getByDateRange(@RequestParam(required = false) Date startDate,@RequestParam(required = false) Date endDate, Pageable page){
        return expService.getByDateRange(startDate,endDate,page).stream().toList();
    }
}
