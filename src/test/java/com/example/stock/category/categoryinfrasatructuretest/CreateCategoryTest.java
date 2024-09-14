package com.example.stock.category.categoryinfrasatructuretest;

import com.example.stock.application.category.command.CategoryCreateHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.infrastructure.category.rest.controller.CategoryCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateCategoryTest {
    @Mock
    private CategoryCreateHandler categoryCreateHandler;

    @InjectMocks
    private CategoryCommandController categoryCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategory_successful() {
        // arrange
        CategoryDto mockCategoryDto = new CategoryDto();
        mockCategoryDto.setName("Test Article");
        mockCategoryDto.setDescription("This is a test category");
        CategoryCreateCommand mockCreateCommand = new CategoryCreateCommand();
        mockCreateCommand.setName("Test Article");
        mockCreateCommand.setDescription("This is a test category");
        when(categoryCreateHandler.execute(any(CategoryCreateCommand.class))).thenReturn(mockCategoryDto);
        // act
        CategoryDto result = categoryCommandController.create(mockCreateCommand);
        // assert
        assertEquals(mockCategoryDto.getName(), result.getName());
        assertEquals(mockCategoryDto.getDescription(), result.getDescription());

    }
    @Test
    void createCategory_whenNameExist_shouldThrowsCategoryException() {
        // arrange
        CategoryCreateCommand mockCreateCommand = new CategoryCreateCommand();
        mockCreateCommand.setName("Existing Article Name");
        mockCreateCommand.setDescription("This is a test category");
        when(categoryCreateHandler.execute(any(CategoryCreateCommand.class)))
                .thenThrow(new CategoryException("Article with this name already exists"));
        // act & Assert
        assertThrows(CategoryException.class, () -> {
            categoryCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createCategory_whenNameExceedsMaxLength_shouldThrowCategoryException() {
        // arrange
        CategoryCreateCommand mockCreateCommand = new CategoryCreateCommand();
        mockCreateCommand.setName("This name is way too long and exceeds fifty characters");
        mockCreateCommand.setDescription("Valid description");
        when(categoryCreateHandler.execute(any(CategoryCreateCommand.class)))
                .thenThrow(new CategoryException("Name exceeds maximum length of 50 characters"));
        // act & Assert
        assertThrows(CategoryException.class, () -> {
            categoryCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createCategory_whenDescriptionIsMissing_shouldThrowCategoryException() {
        // arrange
        CategoryCreateCommand mockCreateCommand = new CategoryCreateCommand();
        mockCreateCommand.setName("Valid Name");
        when(categoryCreateHandler.execute(any(CategoryCreateCommand.class)))
                .thenThrow(new CategoryException("Description is required"));
        // act & Assert
        assertThrows(CategoryException.class, () -> {
            categoryCommandController.create(mockCreateCommand);
        });
    }
    @Test
    void createCategory_whenDescriptionExceedsMaxLength_shouldThrowCategoryException() {
        // arrange
        CategoryCreateCommand mockCreateCommand = new CategoryCreateCommand();
        mockCreateCommand.setName("Valid Name");
        mockCreateCommand.setDescription("This description is way too long and exceeds the ninety characters limit imposed by the system");
        when(categoryCreateHandler.execute(any(CategoryCreateCommand.class)))
                .thenThrow(new CategoryException("Description exceeds maximum length of 90 characters"));
        // act & Assert
        assertThrows(CategoryException.class, () -> {
            categoryCommandController.create(mockCreateCommand);
        });
    }

}
