package com.example.stock.category.categoryinfrasatructuretest;

import com.example.stock.application.category.query.CategoryAllHandler;
import com.example.stock.application.category.query.CategoryByName;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.infrastructure.category.rest.controller.CategoryQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryQueryControllerTest {

    @Mock
    private CategoryAllHandler categoryAllHandler;

    @Mock
    private CategoryByName categoryByName;

    @InjectMocks
    private CategoryQueryController categoryQueryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getByName_ShouldReturnCategory_WhenFound() {
        // Arrange
        String categoryName = "Electronics";
        CategoryDto expectedCategory = new CategoryDto(); // Popula con los campos necesarios

        when(categoryByName.execute(categoryName)).thenReturn(expectedCategory);

        // Act
        CategoryDto actualCategory = categoryQueryController.getByName(categoryName);

        // Assert
        assertEquals(expectedCategory, actualCategory);
        verify(categoryByName, times(1)).execute(categoryName);
    }

    @Test
    public void getAll_ShouldReturnListOfCategories() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;
        List<CategoryDto> expectedCategories = new ArrayList<>(); // Añade algunos objetos CategoryDto si es necesario

        when(categoryAllHandler.execute(page, size, ascending)).thenReturn(expectedCategories);

        // Act
        List<CategoryDto> actualCategories = categoryQueryController.getAll(page, size, ascending);

        // Assert
        assertEquals(expectedCategories, actualCategories);
        verify(categoryAllHandler, times(1)).execute(page, size, ascending);
    }
}
