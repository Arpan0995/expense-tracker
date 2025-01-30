package com.arpansharma.expense_tracker_api.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    @NotBlank(message = "Name cannot be null")
    @Size(min = 3, message = "Name has to be at least 3 characters long")
    private String name;

    private String description;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @NotBlank(message = "Category Id cannot be blank")
    private String categoryId;

    @NotNull(message = "Date cannot be null")
    private Date date;
}
