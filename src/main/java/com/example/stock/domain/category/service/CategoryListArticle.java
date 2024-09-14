package com.example.stock.domain.category.service;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.example.stock.domain.static_variables.StaticData.MESSAGE_ERROR_CATEGORY_NULL;
import static com.example.stock.domain.static_variables.StaticData.MESSAGE_ERROR_CATEGORY_FOUND;
import static com.example.stock.domain.static_variables.StaticData.MESSAGE_ERROR_CATEGORY;
import static com.example.stock.domain.static_variables.StaticData.MESSAGE_ERROR_CATEGORY_DUPLICATED;

@AllArgsConstructor
public class CategoryListArticle {
    private final CategoryDao categoryDao;


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
                throw new ArticleException(MESSAGE_ERROR_CATEGORY_DUPLICATED + number);
            }
        }

        return Arrays.stream(categories)
                .map(this::getCategoryById)
                .toArray(Category[]::new);
    }

    private Category getCategoryById(Long id){
        Category category = categoryDao.getById(id);
        if (category == null)
            throw new CategoryException(MESSAGE_ERROR_CATEGORY_FOUND + id);
        return category;
    }


}
