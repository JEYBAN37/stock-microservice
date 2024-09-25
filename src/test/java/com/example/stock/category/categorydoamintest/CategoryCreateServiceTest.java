package com.example.stock.category.categorydoamintest;


import com.example.stock.category.instance.Dao;
import com.example.stock.category.instance.Repository;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.CategoryCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryCreateServiceTest {
    private CategoryCreateService categoryCreateService;

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
        categoryCreateService = new CategoryCreateService(categoryRepository, categoryDao);
    }
    @Test
     void createsCategory_successfully() {
        //arrange
        CategoryCreateCommand command = new CategoryCreateCommand("Category3","dsfd");
        // act
        Category createdCategory = categoryCreateService.execute(command);
        // assert
        assertNotNull(createdCategory);
        assertEquals("CATEGORY3", createdCategory.getName());
        assertEquals(3L, createdCategory.getId()); // Assuming auto-increment logic

    }

    @Test
    void createCategory_whenEmptyName_shouldThrowsCategoryException() {
        //arrange
        CategoryCreateCommand command = new CategoryCreateCommand("", "description");
        //act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryCreateService.execute(command);
        });
        //assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }
    @Test
    void createCategory_whenNameTooLong_shouldThrowsCategoryException() {
        // arrange
        String longName = "A".repeat(51);
        CategoryCreateCommand command = new CategoryCreateCommand(longName, "description");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryCreateService.execute(command);
        });
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }
    @Test
    void createCategory_whenEmptyDescription_shouldThrowsCategoryException() {
        //arrange
        CategoryCreateCommand command = new CategoryCreateCommand("longName", "");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryCreateService.execute(command);
        });
        //assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }
    @Test
    void createCategory_whenDescriptionTooLong_shouldThrowsCategoryException() {
        //Arrange
        String longDescription = "A".repeat(91);
        CategoryCreateCommand command = new CategoryCreateCommand("longName", longDescription);

      //Act
        CategoryException exception = assertThrows(CategoryException.class, () -> {
            categoryCreateService.execute(command);
        });
        //Assert
        assertEquals("Description don't be bigger than 90 characters", exception.getErrorMessage());
    }

}
