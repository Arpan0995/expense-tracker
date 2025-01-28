package com.arpansharma.expense_tracker_api.controller;

import com.arpansharma.expense_tracker_api.models.Expense;
import com.arpansharma.expense_tracker_api.service.ExpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "Expense APIs", description = "How to use Expense APIs")
public class ExpenseController {

    private final ExpService expService;

    public ExpenseController(ExpService expService){
        this.expService = expService;
    }

    @Operation(summary = "All Expenses")
    @ApiResponse(responseCode = "200",description = "List of All Expenses")
    @GetMapping("/allExpenses")
    public List<Expense> fetchAllExpenses(Pageable page){
        return expService.getExpenses(page).toList();
    }

    @Operation(summary = "Expense Info")
    @ApiResponse(responseCode = "200",description = "Expense Info")
    @GetMapping("/expenses/{id}")
    public Expense fetchExpenseById(@PathVariable Long id){
        return expService.getExpenseById(id);
    }

    @Operation(summary = "Delete an expense")
    @ApiResponse(responseCode = "200",description = "Deleting an expense")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void deleteExpense(@RequestParam("id") Long id){
        expService.deleteExpense(id);
    }

    @Operation(summary = "Adding an Expense")
    @ApiResponse(responseCode = "200",description = "Adds an expense")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addExpense")
    public Expense addExpense(@Valid @RequestBody Expense expense){
        return expService.insertExpense(expense);
    }

    @Operation(summary = "Updating an Expense")
    @ApiResponse(responseCode = "200",description = "Updates an expense")
    @PutMapping("/updateExpense/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id){
        return expService.updateExpense(id, expense);
    }

    @Operation(summary = "Filter by Expense")
    @ApiResponse(responseCode = "200",description = "Filter by expense")
    @GetMapping("/expenses/category")
    public List<Expense> getByCategory(@RequestParam String category, Pageable page){
        return expService.getByCategory(category,page).stream().toList();
    }

    @Operation(summary = "Filter by Name")
    @ApiResponse(responseCode = "200",description = "Filter by name")
    @GetMapping("/expenses/name")
    public List<Expense> getByName(@RequestParam String name, Pageable page){
        return expService.getByName(name, page).stream().toList();
    }

    @Operation(summary = "Filter by Date")
    @ApiResponse(responseCode = "200",description = "Filter by date")
    @GetMapping("/expenses/date")
    public List<Expense> getByDateRange(@RequestParam(required = false) Date startDate,@RequestParam(required = false) Date endDate, Pageable page){
        return expService.getByDateRange(startDate,endDate,page).stream().toList();
    }
}
