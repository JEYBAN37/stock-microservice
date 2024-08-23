package com.example.stock.category.categorydoamintest;



import com.example.stock.category.instance.Dao;
import com.example.stock.category.instance.Repository;
import com.example.stock.domain.category.model.dto.command.CategoryEditCommand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.CategoryUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryUpdateServiceTest {
    private CategoryUpdateService categoryUpdateService;
    @BeforeEach
    public void setUp() {
        List<Category> initialCategories = Arrays.asList(
                new Category(1L, "Category1","d"),
                new Category(2L, "Category2","d"),
                new Category(3L, "Category3","d")
        );

        // Create DAO and Repository with the sample category list
        CategoryDao categoryDao = new Dao(new ArrayList<>(initialCategories));
        CategoryRepository categoryRepository = new Repository(new ArrayList<>(initialCategories));

        // Create the service instance
        categoryUpdateService = new CategoryUpdateService(categoryRepository, categoryDao);
    }

    @Test
    void updateCategory_UpdateCategory_Successfully() {
        // arrange
        CategoryEditCommand command = new CategoryEditCommand("Category3","dsfd");
        // act
        Category updateCategory = categoryUpdateService.execute(command,1L);
        // assert
        assertNotNull(updateCategory);
        assertEquals("Category3", updateCategory.getName());
        assertEquals(1L, updateCategory.getId());

    }
    @Test
    void updateCategory_UpdateCategory_ThrowsCategoryException() {
        // arrange
        CategoryEditCommand command = new CategoryEditCommand("Category3","dsfd");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryUpdateService.execute(command, 5L);
        });
        // assert
        assertEquals("Brand No Exist", exception.getErrorMessage());
    }
    @Test
    void updateCategory_whenEmptyName_shouldThrowsCategoryException() {
        // arrange
        CategoryEditCommand command = new CategoryEditCommand("", "description");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryUpdateService.execute(command,1L );
        });
        // assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }

    @Test
    void updateCategory_whenNameTooLong_shouldThrowsCategoryException() {
        // arrange
        String longName = "A".repeat(51);
        CategoryEditCommand command = new CategoryEditCommand(longName,"dsfd");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryUpdateService.execute(command, 1L);
        });
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }
    @Test
    void updateCategory_whenDescriptionTooLong_shouldThrowsCategoryException() {
        // arrange
        String longDescription = "A".repeat(91);
        CategoryEditCommand command = new CategoryEditCommand("longName", longDescription);
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryUpdateService.execute(command, 1L);
        });
        // assert
        assertEquals("Description don't be bigger than 90 characters", exception.getErrorMessage());
    }



}
