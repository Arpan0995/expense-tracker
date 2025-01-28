package com.arpansharma.expense_tracker_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDTO {

    private String categoryId;
    private String name;
    private String description;
    private Timestamp createdTs;
    private Timestamp updatedTs;
    private UserDTO user;
}
