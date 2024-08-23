package com.example.stock.domain.brand.service;

import com.example.stock.domain.brand.model.exception.CategoryException;
import com.example.stock.domain.brand.port.dao.CategoryDao;
import com.example.stock.domain.brand.port.repository.CategoryRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CategoryDeleteService {
    private final CategoryRepository categoryRepository;
    private final CategoryDao categoryDao;
    private static final String MESSAGE_ERROR_REMOVE = "Error to Remove No Exist";

    public void execute(Long id){
        if (!categoryDao.idExist(id))
            throw new CategoryException(MESSAGE_ERROR_REMOVE);
        categoryRepository.deleteById(id);
    }
}
