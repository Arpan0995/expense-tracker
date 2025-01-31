package com.arpansharma.expense_tracker_api.mappers;

import com.arpansharma.expense_tracker_api.dto.CategoryDTO;
import com.arpansharma.expense_tracker_api.io.CategoryRequest;
import com.arpansharma.expense_tracker_api.io.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse mapToResponse(CategoryDTO categoryDTO);

    CategoryDTO mapToDto(CategoryRequest categoryRequest);
}
