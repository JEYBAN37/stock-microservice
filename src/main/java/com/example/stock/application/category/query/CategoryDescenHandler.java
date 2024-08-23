package com.example.stock.application.category.query;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.port.dao.CategoryDao;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoryDescenHandler {
    private final CategoryDao categoryDao;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryDescenHandler(CategoryDao categoryDao, CategoryDtoMapper categoryDtoMapper) {
        this.categoryDao = categoryDao;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public List<CategoryDto> execute() {
        return categoryDao.getFilterDsc()
                .stream()
                .map(categoryDtoMapper::toDto)
                .toList();
    }
}
