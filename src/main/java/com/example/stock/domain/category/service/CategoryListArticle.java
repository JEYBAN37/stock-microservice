package com.example.stock.domain.category.service;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.stock.domain.static_variables.StaticData.*;

@AllArgsConstructor
public class CategoryListArticle {
    private final CategoryDao categoryDao;


    public List<Category> execute (List<Long> categoryEntities){
        return verifyCategories(categoryEntities);
    }

    private List<Category>  verifyCategories (List<Long> categories){
        if (categories == null || categories.isEmpty()){
            throw new ArticleException(MESSAGE_ERROR_CATEGORY_NULL) ;
        }

        if (categories.size() > THREE_CONSTANT)
            throw new ArticleException(MESSAGE_ERROR_CATEGORY);

        Set<Long> uniqueNumbers = new HashSet<>();
        for (Long number : categories) {
            if (!uniqueNumbers.add(number)) {
                throw new ArticleException(MESSAGE_ERROR_CATEGORY_DUPLICATED + number);
            }
        }

        return categories.stream()
                .map(this::getCategoryById)
                .toList();
    }

    private Category getCategoryById(Long id){
        Category category = categoryDao.getById(id);
        if (category == null)
            throw new CategoryException(MESSAGE_ERROR_CATEGORY_FOUND + id);
        return category;
    }
}
