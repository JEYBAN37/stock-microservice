package com.example.stock.domain.category.service;


import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;

import static com.example.stock.domain.static_variables.StaticData.MESSAGE_ERROR_ADD_CATEGORY;

public class CategoryCreateService {
    private final CategoryRepository categoryRepository;
    private final CategoryDao categoryDao;

    public CategoryCreateService(CategoryRepository categoryRepository, CategoryDao categoryDao) {
        this.categoryRepository = categoryRepository;
        this.categoryDao = categoryDao;
    }


    public Category execute (CategoryCreateCommand categoryCreateCommand){
        if (categoryDao.nameExist(categoryCreateCommand.getName()))
            throw new CategoryException( MESSAGE_ERROR_ADD_CATEGORY);
        Category categoryToCreate = new Category().requestToCreate(categoryCreateCommand);
        return categoryRepository.create(categoryToCreate);
    }
}
