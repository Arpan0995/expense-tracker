package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;
import com.arpansharma.expense_tracker_api.dto.UserDTO;
import com.arpansharma.expense_tracker_api.exception.ItemAlreadyExistsException;
import com.arpansharma.expense_tracker_api.exception.ResourceNotFoundException;
import com.arpansharma.expense_tracker_api.models.Category;
import com.arpansharma.expense_tracker_api.models.User;
import com.arpansharma.expense_tracker_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final UserService userService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserService userService){
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
         List<Category> categoryList =  categoryRepository.findByUserId(userService.getLoggedInuser().getId());
         return categoryList.stream().map(category -> mapToDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if(categoryRepository.existsByNameAndUserId(categoryDTO.getName(),userService.getLoggedInuser().getId())){
            throw new ItemAlreadyExistsException("A Category with this name already exists");
        }else {
            Category category = mapToCategory(categoryDTO);
            categoryRepository.save(category);
            return mapToDto(category);
        }
    }

    @Override
    public void deleteCategory(String categoryId) {
        Optional<Category> category = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInuser().getId(),categoryId);
        if(category.isPresent()){
            categoryRepository.delete(category.get());
        }else{
            throw new ResourceNotFoundException("Category does not exist");
        }
    }

    private Category mapToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(UUID.randomUUID().toString());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setUser(userService.getLoggedInuser());
        return category;
    }



    private CategoryDTO mapToDto(Category category) {
        return CategoryDTO.builder().categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .createdTs(category.getCreatedTs())
                .updatedTs(category.getUpdatedTs())
                .user(mapToUserDto(category.getUser()))
                .build();
    }

    private UserDTO mapToUserDto(User user) {
        return UserDTO.builder().name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
