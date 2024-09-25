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

    public List<Category> execute(List<Long> categoryEntities) {
        return verifyCategories(categoryEntities);
    }
    private List<Category> verifyCategories(List<Long> categories) {
        validateCategoryList(categories);
        Set<Long> uniqueCategoryIds = new HashSet<>();

        return categories.stream()
                .map(categoryId -> {
                    Category category = validateAndFetchCategory(categoryId);
                    if (!uniqueCategoryIds.add(category.getId())) {
                        throw new ArticleException(MESSAGE_ERROR_CATEGORY_DUPLICATED + category.getId());
                    }
                    return category;
                })
                .toList();
    }

    private void validateCategoryList(List<Long> categories) {
        if (categories == null || categories.isEmpty()) {
            throw new ArticleException(MESSAGE_ERROR_CATEGORY_NULL);
        }
        if (categories.size() > THREE_CONSTANT) {
            throw new ArticleException(MESSAGE_ERROR_CATEGORY_SIZE);
        }
        for (Long categoryId : categories) {
            if (categoryId <= ZERO_CONSTANT) {
                throw new ArticleException(MESSAGE_ERROR_CATEGORY_CERO + categoryId);
            }
        }
    }

    private Category validateAndFetchCategory(Long id) {
        Category category = categoryDao.getById(id);
        if (category == null) {
            throw new CategoryException(MESSAGE_ERROR_CATEGORY + id);
        }
        return category;
    }


}
