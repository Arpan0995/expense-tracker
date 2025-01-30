package com.arpansharma.expense_tracker_api.dto;

import com.arpansharma.expense_tracker_api.models.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO {

    private String expenseId;
    private String name;
    private String description;
    private BigDecimal amount;
    private Date date;
    private Timestamp createdTs;
    private Timestamp updatedTs;
    private String categoryId;
    private CategoryDTO categoryDTO;
    private UserDTO userDTO;
}

