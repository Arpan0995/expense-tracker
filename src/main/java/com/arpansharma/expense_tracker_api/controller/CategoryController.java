package com.arpansharma.expense_tracker_api.controller;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;
import com.arpansharma.expense_tracker_api.io.CategoryRequest;
import com.arpansharma.expense_tracker_api.io.CategoryResponse;
import com.arpansharma.expense_tracker_api.mappers.CategoryMapper;
import com.arpansharma.expense_tracker_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryDTO categoryDTO = categoryMapper.mapToDto(categoryRequest);
        categoryDTO = categoryService.createCategory(categoryDTO);
        return categoryMapper.mapToResponse(categoryDTO);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        List<CategoryDTO> categoryDTOList= categoryService.getAllCategories();
        return categoryDTOList.stream().map(categoryDTO -> categoryMapper.mapToResponse(categoryDTO)).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId){
        categoryService.deleteCategory(categoryId);
    }

}
