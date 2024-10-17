package com.example.stock.domain.category.service;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.example.stock.domain.static_variables.StaticData.MESSAGE_PAGE_VALID;

@AllArgsConstructor
public class CategoryFilterService {
    private final CategoryDtoMapper categoryDtoMapper;
    private final CategoryDao categoryDao;
    public List<Category> execute(int page, int size, boolean ascending) {
        if (page < 0 || size <= 0) {
            throw new ArticleException(MESSAGE_PAGE_VALID);
        }
        return categoryDao.getAll(page, size, ascending);
    }

}
