package com.example.stock.domain.category.service;

import com.example.stock.domain.category.model.dto.command.CategoryEditCommand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryUpdateService {
    private final CategoryRepository categoryRepository;
    private final CategoryDao categoryDao;
    private static final String MESSAGE_ERROR_UPDATE = "Brand No Exist";
    public Category execute(CategoryEditCommand categoryEditCommand, Long id) {
        Category currentCategory = categoryDao.getById(id);
        if (currentCategory == null)
            throw new CategoryException(MESSAGE_ERROR_UPDATE);
        Category categoryUpdate = new Category(
                currentCategory.getId(),
                categoryEditCommand.getName(),
                categoryEditCommand.getDescription()
        );
        return categoryRepository.update(categoryUpdate);
    }
}
