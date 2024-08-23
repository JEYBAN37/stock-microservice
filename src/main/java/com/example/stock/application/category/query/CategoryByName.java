package com.example.stock.application.category.query;


import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.port.dao.CategoryDao;
import org.springframework.stereotype.Component;

@Component
public class CategoryByName {
    private final CategoryDao categoryDao;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryByName(CategoryDao categoryDao, CategoryDtoMapper categoryDtoMapper) {
        this.categoryDao = categoryDao;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public CategoryDto execute(String name) {
        return categoryDtoMapper.toDto(categoryDao.getByName(name));
    }
}
