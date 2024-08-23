package com.example.stock.application.category.query;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.port.dao.CategoryDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CategoryAllHandler {
    private final CategoryDao categoryDao;
    private final CategoryDtoMapper categoryDtoMapper;

    public List<CategoryDto> execute(int page, int size, boolean ascending) {
        return categoryDao.getAll(page, size, ascending)
                .stream()
                .map(categoryDtoMapper::toDto)
                .toList();
    }

}
