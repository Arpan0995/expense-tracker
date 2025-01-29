package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public List<CategoryDTO> getAllCategories();

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    void deleteCategory(String categoryId);
}
