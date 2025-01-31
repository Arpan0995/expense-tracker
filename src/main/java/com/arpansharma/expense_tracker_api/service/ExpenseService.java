package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;
import com.arpansharma.expense_tracker_api.dto.ExpenseDTO;
import com.arpansharma.expense_tracker_api.exception.ResourceNotFoundException;
import com.arpansharma.expense_tracker_api.models.Category;
import com.arpansharma.expense_tracker_api.models.Expense;
import com.arpansharma.expense_tracker_api.repository.CategoryRepository;
import com.arpansharma.expense_tracker_api.repository.ExpenseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseService implements ExpService {

    private final ExpenseRepository expenseRepository;

    private final UserService userService;

    private CategoryRepository categoryRepository;


    public ExpenseService(ExpenseRepository expenseRepository,
                          UserService userService, CategoryRepository categoryRepository){
        this.expenseRepository = expenseRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ExpenseDTO> getExpenses(Pageable page){
        List<Expense> expenses = expenseRepository.findByUserId(userService.getLoggedInuser().getId(),page).toList();
        List<ExpenseDTO> expenseDTOS = expenses.stream().map(expense -> mapToExpenseDto(expense)).collect(Collectors.toList());
        return  expenseDTOS;
    }

    @Override
    public ExpenseDTO getExpenseById(String expenseId) {
        Expense expense = getExpense(expenseId);
        ExpenseDTO expenseDTO = mapToExpenseDto(expense);
        return expenseDTO;
    }

    private Expense getExpense(String expenseId) {
        Optional<Expense> expense =  expenseRepository.findByUserIdAndExpenseId(userService.getLoggedInuser().getId(), expenseId);
        if(!expense.isPresent()){
            throw new ResourceNotFoundException("Expense does not exist with id: " + expenseId);
        }
        return expense.get();
    }

    @Override
    public void deleteExpense(String expenseId) {
        Expense expense = getExpense(expenseId);
        expenseRepository.delete(expense);
    }

    @Override
    public ExpenseDTO insertExpense(ExpenseDTO expenseDTO) {
        Optional<Category> category = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInuser().getId(), expenseDTO.getCategoryId());
        if(!category.isPresent()){
            throw new ResourceNotFoundException("Category does not exist for id " + expenseDTO.getCategoryId());
        }
        Expense expense = mapToExpense(expenseDTO);
        expense.setUser(userService.getLoggedInuser());
        expense.setCategory(category.get());
        expenseRepository.save(expense);
        return mapToExpenseDto(expense);

    }

    private ExpenseDTO mapToExpenseDto(Expense expense) {
        return ExpenseDTO.builder().name(expense.getName())
                .description(expense.getDescription())
                .amount(expense.getAmount())
                .createdTs(expense.getCreateTs())
                .updatedTs(expense.getUpdateTs())
                .expenseId(expense.getExpenseId())
                .categoryDTO(mapToCategoryDto(expense.getCategory()))
                .build();
    }

    private CategoryDTO mapToCategoryDto(Category category) {
        return CategoryDTO.builder().categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .createdTs(category.getCreatedTs())
                .updatedTs(category.getUpdatedTs())
                .build();
    }

    private Expense mapToExpense(ExpenseDTO expenseDTO) {
       return Expense.builder().name(expenseDTO.getName())
                .description(expenseDTO.getDescription())
                .expenseId(UUID.randomUUID().toString())
                .amount(expenseDTO.getAmount())
                .date(expenseDTO.getDate())
                .build();
    }


    @Override
    public ExpenseDTO updateExpense(String expenseId, ExpenseDTO expenseDTO) {
        if(expenseDTO.getCategoryDTO() != null) {
            Optional<Category> category = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInuser().getId(), expenseDTO.getCategoryId());
            if (!category.isPresent()) {
                throw new ResourceNotFoundException("Category does not exist, please create a category first.");
            }
        }
        Expense existingExpense = getExpense(expenseId);
        existingExpense.setName(expenseDTO.getName() != null? expenseDTO.getName():existingExpense.getName());
        existingExpense.setDescription(expenseDTO.getDescription() != null?expenseDTO.getDescription(): existingExpense.getDescription());
        existingExpense.setAmount(expenseDTO.getAmount() != null?expenseDTO.getAmount():existingExpense.getAmount());
        existingExpense.setDate(expenseDTO.getDate() != null?expenseDTO.getDate():existingExpense.getDate());


        expenseRepository.save(existingExpense);
        expenseDTO = mapToExpenseDto(existingExpense);

        return expenseDTO;
    }

    @Override
    public List<ExpenseDTO> getByCategory(String categoryId, Pageable page) {
        Optional<Category> category = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInuser().getId(),categoryId);
        if(!category.isPresent()){
            throw new ResourceNotFoundException("No category with category id: " + categoryId);
        }
        List<Expense> expenses = expenseRepository.findByUserIdAndCategory(userService.getLoggedInuser().getId(),category.get(),page).toList();
        List<ExpenseDTO> expenseDTOS = expenses.stream().map(expense -> mapToExpenseDto(expense)).collect(Collectors.toList());
        return  expenseDTOS;
    }

    @Override
    public List<ExpenseDTO> getByName(String name, Pageable page) {
        List<Expense> expense = expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInuser().getId(),name,page).toList();
        List<ExpenseDTO> expenseDTOS = expense.stream().map(expense1 -> mapToExpenseDto(expense1)).collect(Collectors.toList());
        return  expenseDTOS;
    }

    @Override
    public List<ExpenseDTO> getByDateRange(@RequestParam Date startDate,@RequestParam Date endDate, Pageable page) {
        if(startDate == null){
            startDate = new Date(0);
        }
        if(endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
        List<Expense> expense = expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInuser().getId(), startDate, endDate, page).toList();
        List<ExpenseDTO> expenseDTOS = expense.stream().map(expense1 -> mapToExpenseDto(expense1)).collect(Collectors.toList());
        return expenseDTOS;
    }
}
