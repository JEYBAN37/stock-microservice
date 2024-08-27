package com.example.stock.domain.category.service;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class CategoryListArticle {
    private final CategoryDao categoryDao;
    private static final String MESSAGE_ERROR_CATEGORY = "Category height Invalid";
    private static final String MESSAGE_ERROR_CATEGORY_NULL = "Category Array Null";

    public Category[] execute (Long[] categoryEntities){
        return verifyCategories(categoryEntities);
    }

    private Category[]  verifyCategories ( Long[] categories){
        if (categories == null || categories.length == 0){
            throw new ArticleException(MESSAGE_ERROR_CATEGORY_NULL) ;
        }

        if (categories.length > 3)
            throw new ArticleException(MESSAGE_ERROR_CATEGORY);

        Set<Long> uniqueNumbers = new HashSet<>();
        for (Long number : categories) {
            if (!uniqueNumbers.add(number)) {
                throw new ArticleException("Category duplicated: " + number);
            }
        }

        return Arrays.stream(categories)
                .map(this::getCategoryById)
                .toArray(Category[]::new);
    }

    private Category getCategoryById(Long id){
        Category category = categoryDao.getById(id);
        if (category == null)
            throw new CategoryException("Category no found: " + id);
        return category;
    }


}
