package com.example.stock.domain.category.service;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.port.dao.CategoryDao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CategoryFilterService {
    private final CategoryDtoMapper categoryDtoMapper;
    private final CategoryDao categoryDao;
    public List<CategoryDto> execute(int page, int size, boolean ascending) {
        return categoryDao.getAll(page, size, ascending)
                .stream()
                .map(categoryDtoMapper::toDto)
                .toList();
    }

}
