package com.example.stock.domain.brand.service;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.dto.command.BrandEditCommand;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrandUpdateService {
    private final BrandRepository categoryRepository;
    private final BrandDao categoryDao;
    private static final String MESSAGE_ERROR_UPDATE = "Brand No Exist";
    private static final String MESSAGE_ERROR_ADD = "Brand with name Exist";
    public Brand execute(BrandEditCommand brandEditCommand, Long id) {
        Brand currentCategory = categoryDao.getById(id);
        if (currentCategory == null) {
            throw new BrandException(MESSAGE_ERROR_UPDATE);
        }

        if (!currentCategory.getName().equals(brandEditCommand.getName()) && categoryDao.nameExist(brandEditCommand.getName())) {
            throw new ArticleException(MESSAGE_ERROR_ADD);
        }

        Brand categoryUpdate = new Brand(
                currentCategory.getId(),
                brandEditCommand.getName(),
                brandEditCommand.getDescription()
        );
        return categoryRepository.update(categoryUpdate);
    }
}
