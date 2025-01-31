package com.arpansharma.expense_tracker_api.controller;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;
import com.arpansharma.expense_tracker_api.dto.ExpenseDTO;
import com.arpansharma.expense_tracker_api.io.CategoryResponse;
import com.arpansharma.expense_tracker_api.mappers.ExpenseMapper;
import com.arpansharma.expense_tracker_api.models.Expense;
import com.arpansharma.expense_tracker_api.io.ExpenseRequest;
import com.arpansharma.expense_tracker_api.io.ExpenseResponse;
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
import java.util.stream.Collectors;

@RestController
@Tag(name = "Expense APIs", description = "How to use Expense APIs")
public class ExpenseController {

    private final ExpService expService;

    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpService expService, ExpenseMapper expenseMapper){
        this.expService = expService;
        this.expenseMapper = expenseMapper;
    }

    @Operation(summary = "All Expenses")
    @ApiResponse(responseCode = "200",description = "List of All Expenses")
    @GetMapping("/allExpenses")
    public List<ExpenseResponse> fetchAllExpenses(Pageable page){
        List<ExpenseDTO> expenseDTOS = expService.getExpenses(page);
        List<ExpenseResponse> expenseResponses = expenseDTOS.stream().map(expenseDTO -> expenseMapper.maptoExpenseResponse(expenseDTO)).collect(Collectors.toList());
        return expenseResponses;
    }

    @Operation(summary = "Expense Info")
    @ApiResponse(responseCode = "200",description = "Expense Info")
    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse fetchExpenseById(@PathVariable String expenseId){
        ExpenseDTO expenseDTO = expService.getExpenseById(expenseId);
        ExpenseResponse expenseResponse = expenseMapper.maptoExpenseResponse(expenseDTO);
        return expenseResponse;
    }

    @Operation(summary = "Delete an expense")
    @ApiResponse(responseCode = "200",description = "Deleting an expense")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void deleteExpense(@RequestParam("id") String id){
        expService.deleteExpense(id);
    }

    @Operation(summary = "Adding an Expense")
    @ApiResponse(responseCode = "201",description = "Adds an expense")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addExpense")
    public ExpenseResponse addExpense(@Valid @RequestBody ExpenseRequest expenseRequest){
        ExpenseDTO expenseDTO = expenseMapper.mapToExpenseDto(expenseRequest);
        expenseDTO = expService.insertExpense(expenseDTO);
        return expenseMapper.maptoExpenseResponse(expenseDTO);
    }

    @Operation(summary = "Updating an Expense")
    @ApiResponse(responseCode = "200",description = "Updates an expense")
    @PutMapping("/updateExpense/{expenseId}")
    public ExpenseResponse updateExpense(@RequestBody ExpenseRequest expenseRequest, @PathVariable String expenseId){
        ExpenseDTO expenseDTO = expenseMapper.mapToExpenseDto(expenseRequest);
        expenseDTO = expService.updateExpense(expenseId, expenseDTO);
        return expenseMapper.maptoExpenseResponse(expenseDTO);
    }

    @Operation(summary = "Filter by Expense")
    @ApiResponse(responseCode = "200",description = "Filter by expense")
    @GetMapping("/expenses/category")
    public List<ExpenseResponse> getByCategory(@RequestParam String categoryId, Pageable page){
        List<ExpenseDTO> expenseDTOS = expService.getByCategory(categoryId,page).stream().toList();
        List<ExpenseResponse> expenseResponses = expenseDTOS.stream().map(expenseDTO -> expenseMapper.maptoExpenseResponse(expenseDTO)).collect(Collectors.toList());
        return expenseResponses;
    }

    @Operation(summary = "Filter by Name")
    @ApiResponse(responseCode = "200",description = "Filter by name")
    @GetMapping("/expenses/name")
    public List<ExpenseResponse> getByName(@RequestParam String name, Pageable page){
        List<ExpenseDTO> expenseDTOS =  expService.getByName(name, page).stream().toList();
        List<ExpenseResponse> expenseResponses = expenseDTOS.stream().map(expenseDTO -> expenseMapper.maptoExpenseResponse(expenseDTO)).collect(Collectors.toList());
        return  expenseResponses;
    }

    @Operation(summary = "Filter by Date")
    @ApiResponse(responseCode = "200",description = "Filter by date")
    @GetMapping("/expenses/date")
    public List<ExpenseResponse> getByDateRange(@RequestParam(required = false) Date startDate,@RequestParam(required = false) Date endDate, Pageable page){
        List<ExpenseDTO> expenseDTOS = expService.getByDateRange(startDate,endDate,page).stream().toList();
        List<ExpenseResponse> expenseResponses = expenseDTOS.stream().map(expenseDTO -> expenseMapper.maptoExpenseResponse(expenseDTO)).collect(Collectors.toList());
        return expenseResponses;
    }
}
