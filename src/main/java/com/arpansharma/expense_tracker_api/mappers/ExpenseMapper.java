package com.arpansharma.expense_tracker_api.mappers;

import com.arpansharma.expense_tracker_api.dto.ExpenseDTO;
import com.arpansharma.expense_tracker_api.io.ExpenseRequest;
import com.arpansharma.expense_tracker_api.io.ExpenseResponse;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(source = "expenseDTO.categoryDTO", target = "categoryResponse")
    ExpenseResponse maptoExpenseResponse(ExpenseDTO expenseDTO);

    ExpenseDTO mapToExpenseDto(@Valid ExpenseRequest expenseRequest);
}
