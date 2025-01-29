package com.arpansharma.expense_tracker_api.controller;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;
import com.arpansharma.expense_tracker_api.models.Category;
import com.arpansharma.expense_tracker_api.models.CategoryRequest;
import com.arpansharma.expense_tracker_api.models.CategoryResponse;
import com.arpansharma.expense_tracker_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryDTO categoryDTO = mapToDto(categoryRequest);
        categoryDTO = categoryService.createCategory(categoryDTO);
        return mapToResponse(categoryDTO);
    }

    private CategoryResponse mapToResponse(CategoryDTO categoryDTO) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryId(categoryDTO.getCategoryId());
        categoryResponse.setName(categoryDTO.getName());
        categoryResponse.setDescription(categoryDTO.getDescription());
        categoryResponse.setCreatedTs(categoryDTO.getCreatedTs());
        categoryResponse.setUpdatedTs(categoryDTO.getUpdatedTs());
        return categoryResponse;
    }

    private CategoryDTO mapToDto(CategoryRequest categoryRequest) {
        return CategoryDTO.builder().name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
