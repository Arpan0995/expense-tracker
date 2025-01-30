package com.arpansharma.expense_tracker_api.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String categoryId;
    private String name;
    private String description;
    private Timestamp createdTs;
    private Timestamp updatedTs;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public Timestamp getUpdatedTs() {
        return updatedTs;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    public void setUpdatedTs(Timestamp updatedTs) {
        this.updatedTs = updatedTs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
