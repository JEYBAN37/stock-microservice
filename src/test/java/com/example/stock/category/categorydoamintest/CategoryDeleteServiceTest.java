package com.example.stock.category.categorydoamintest;


import com.example.stock.category.instance.Dao;
import com.example.stock.category.instance.Repository;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.CategoryDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CategoryDeleteServiceTest {
    private CategoryDeleteService categoryDeleteService;
    @BeforeEach
    public void setUp() {
        List<Category> initialCategories = Arrays.asList(
                new Category(1L, "Category1","d"),
                new Category(2L, "Category2","d")
        );

        // Create DAO and Repository with the sample category list
        CategoryDao categoryDao = new Dao(new ArrayList<>(initialCategories));
        CategoryRepository categoryRepository = new Repository(new ArrayList<>(initialCategories));

        // Create the service instance
        categoryDeleteService = new CategoryDeleteService(categoryRepository, categoryDao);
    }

    @Test
    void deleteCategory_whenCategoryNoExist_shouldThrowsCategoryException() {
        //arrange
        Category command = new Category(5l,"Category1","d");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryDeleteService.execute(command.getId());
        });
        //assert
        assertEquals("Error to Remove No Exist", exception.getErrorMessage());
    }

}
