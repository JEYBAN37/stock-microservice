package com.example.stock.categoryinfrasatructuretest;

import com.example.stock.application.category.query.CategoryAllHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.infrastructure.category.rest.controller.CategoryQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class ListCategoriesTest {

    @Mock
    private CategoryAllHandler categoryAllHandler;

    @InjectMocks
    private CategoryQueryController categoryQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAll_shouldReturnCategories() {
        // arrange
        CategoryDto categoryDto1 = new CategoryDto(1L, "Category 1", "Description 1");
        CategoryDto categoryDto2 = new CategoryDto(2L, "Category 2", "Description 2");
        List<CategoryDto> expectedCategories = List.of(categoryDto1, categoryDto2);
        when(categoryAllHandler.execute(0, 10, false)).thenReturn(expectedCategories);
        // act
        List<CategoryDto> result = categoryQueryController.getAll(0, 10, false);
        // assert
        assertEquals(expectedCategories, result);
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNoCategories() {
        // arrange
        when(categoryAllHandler.execute(0, 10, false)).thenReturn(List.of()); // Lista vacía
        // act
        List<CategoryDto> result = categoryQueryController.getAll(0, 10, true);
        // assert
        assertEquals(0, result.size()); // Debe retornar una lista vacía
    }

    @Test
    void getAll_shouldReturnCategoriesInDescendingOrder() {
        // arrange
        CategoryDto categoryDto1 = new CategoryDto(1L, "Category A", "Description A");
        CategoryDto categoryDto2 = new CategoryDto(2L, "Category B", "Description B");
        List<CategoryDto> expectedCategories = List.of(categoryDto2, categoryDto1); // Esperado en orden descendente
        when(categoryAllHandler.execute(0, 10, true)).thenReturn(List.of(categoryDto1, categoryDto2)); // Orden natural
        // Act
        List<CategoryDto> result = categoryQueryController.getAll(0, 10, true);
        // Assert
        assertEquals(expectedCategories.get(0).getName(), result.get(0).getName());
        assertEquals(expectedCategories.get(1).getName(), result.get(1).getName());
    }
}
