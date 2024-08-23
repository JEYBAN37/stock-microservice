package com.example.stock.categoryapplication;


import com.example.stock.application.category.command.CategoryCreateHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.CategoryCreateService;
import com.example.stock.instance.CategoryDtoMapperInstance;
import com.example.stock.instance.Dao;
import com.example.stock.instance.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryCreateHandlerTest {
    private CategoryCreateHandler categoryCreateHandler;
    @BeforeEach
    public void setUp() {
        // Create DAO and Repository with the sample category list
        List<Category> initialCategories = Arrays.asList(
                new Category(1L, "Category1","d"),
                new Category(2L, "Category2","d")
        );
        // Create DAO and Repository with the sample category list
        CategoryDao categoryDao = new Dao(new ArrayList<>(initialCategories));
        CategoryRepository categoryRepository = new Repository(new ArrayList<>(initialCategories));

        // Create the service instance
        CategoryCreateService categoryCreateService = new CategoryCreateService(categoryRepository,categoryDao);
        CategoryDtoMapperInstance categoryDtoMapper = new CategoryDtoMapperInstance();
        categoryCreateHandler = new CategoryCreateHandler(categoryCreateService,categoryDtoMapper);
    }

    @Test
    void handler_createsCategory_successfully() {
        // arrange
        CategoryCreateCommand command = new CategoryCreateCommand("Category3","dsfd");
        // act
        CategoryDto createdCategory = categoryCreateHandler.execute(command);
        // assert
        assertNotNull(createdCategory);
        assertEquals("Category3", createdCategory.getName());
        assertEquals(3L, createdCategory.getId()); // Assuming auto-increment logic
    }
    @Test
    void handler_createsCategory_whenExistCategory_shouldThrowsCategoryException() {
        //arrange
        CategoryCreateCommand command = new CategoryCreateCommand("Category2","dsfd");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> categoryCreateHandler.execute(command));
        // assert
        assertEquals("Category Exist", exception.getErrorMessage());

    }

    @Test
    void handler_createCategory_whenEmptyName_shouldThrowsCategoryException() {
        //arrange
        CategoryCreateCommand command = new CategoryCreateCommand("", "description");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> categoryCreateHandler.execute(command));
        // assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createCategory_whenNameTooLong_shouldThrowsCategoryException() {
        // arrange
        String longName = "A".repeat(51); // 101 characters long
        CategoryCreateCommand command = new CategoryCreateCommand(longName, "description");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> categoryCreateHandler.execute(command));
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }

    @Test
    void handler_createCategory_whenEmptyDescription_shouldThrowsCategoryException() {
        // arrange
        CategoryCreateCommand command = new CategoryCreateCommand("longName", "");
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> categoryCreateHandler.execute(command));
        // assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createCategory_whenDescriptionTooLong_shouldThrowsCategoryException() {
        // arrange
        String longDescription = "A".repeat(91); // 101 characters long
        CategoryCreateCommand command = new CategoryCreateCommand("longName", longDescription);
        // act
        CategoryException exception = assertThrows(CategoryException.class, () -> categoryCreateHandler.execute(command));
        // assert
        assertEquals("Description don't be bigger than 90 characters", exception.getErrorMessage());
    }
}
