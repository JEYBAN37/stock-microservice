package com.example.stock.application.category.command;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryEditCommand;
import com.example.stock.domain.category.service.CategoryUpdateService;
import org.springframework.stereotype.Component;

@Component
public class CategoryUpdateHandler {
    private final CategoryUpdateService categoryUpdateService;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryUpdateHandler(CategoryUpdateService categoryUpdateService, CategoryDtoMapper categoryDtoMapper) {
        this.categoryUpdateService = categoryUpdateService;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public CategoryDto execute (CategoryEditCommand categoryEditCommand, Long id){
        return categoryDtoMapper.toDto(categoryUpdateService.execute(categoryEditCommand,id));
    }
}

