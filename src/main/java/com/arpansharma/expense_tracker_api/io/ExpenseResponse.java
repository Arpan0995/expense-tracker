package com.arpansharma.expense_tracker_api.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExpenseResponse {
private String expenseId;
private String name;
private String description;
private BigDecimal amount;
private Date date;
private Timestamp createdTs;
private Timestamp updatedTs;
private CategoryResponse categoryResponse;
}
