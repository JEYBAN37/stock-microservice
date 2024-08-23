package com.example.stock.domain.brand.service;


import com.example.stock.domain.brand.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.brand.model.entity.Category;
import com.example.stock.domain.brand.model.exception.CategoryException;
import com.example.stock.domain.brand.port.dao.CategoryDao;
import com.example.stock.domain.brand.port.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryCreateService {
    private final CategoryRepository categoryRepository;
    private final CategoryDao categoryDao;

    private static final String MESSAGE_ERROR_ADD = "Category Exist";
    public Category execute (CategoryCreateCommand categoryCreateCommand){
        if (categoryDao.nameExist(categoryCreateCommand.getName()))
            throw new CategoryException(MESSAGE_ERROR_ADD);
        Category categoryToCreate = new Category().requestToCreate(categoryCreateCommand);
        return categoryRepository.create(categoryToCreate);
    }
}
