package com.example.stock.application.category.command;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.service.CategoryCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryCreateHandler {
    private final CategoryCreateService categoryCreateService;
    private final CategoryDtoMapper categoryDtoMapper;

    @Autowired
    public CategoryCreateHandler(CategoryCreateService categoryCreateService, CategoryDtoMapper categoryDtoMapper) {
        this.categoryCreateService = categoryCreateService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public CategoryDto execute (CategoryCreateCommand createCommand){
        return categoryDtoMapper.toDto(categoryCreateService.execute(createCommand));
    }
}
