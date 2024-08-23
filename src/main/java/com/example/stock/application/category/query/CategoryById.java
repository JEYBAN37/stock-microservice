package com.example.stock.application.category.query;


import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.port.dao.CategoryDao;

public class CategoryById {
    private final CategoryDao categoryDao;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryById(CategoryDao categoryDao, CategoryDtoMapper categoryDtoMapper) {
        this.categoryDao = categoryDao;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public CategoryDto execute(Long id) {
        return categoryDtoMapper.toDto(categoryDao.getById(id));
    }
}
