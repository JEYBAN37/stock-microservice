package com.example.stock.category.categoryapplicationtest;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.application.category.query.CategoryAllHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.service.CategoryFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
 class CategoryGetAllHandlreTest {
    @Mock
    private CategoryDao categoryDao;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @InjectMocks
    private CategoryAllHandler categoryAllHandler;
     @Mock
     private CategoryFilterService categoryFilterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

     @Test
     void execute_shouldReturnCategoriesInAscendingOrder() {
         // Arrange
         CategoryDto categoryDto1 = new CategoryDto( "Category A", "Description A");
         CategoryDto categoryDto2 = new CategoryDto( "Category B", "Description B");
         List<CategoryDto> expectedCategories = Arrays.asList(categoryDto1, categoryDto2);
         when(categoryFilterService.execute(0, 10, true)).thenReturn(expectedCategories);
         // Act
         List<CategoryDto> result = categoryAllHandler.execute(0, 10, true);
         // Assert
         assertEquals(expectedCategories, result);
     }


     @Test
     void execute_shouldReturnCategoriesInDescendingOrder() {
         // Arrange
         CategoryDto categoryDto1 = new CategoryDto( "Category A", "Description A");
         CategoryDto categoryDto2 = new CategoryDto( "Category B", "Description B");
         List<CategoryDto> expectedCategories = Arrays.asList(categoryDto2, categoryDto1);
         when(categoryFilterService.execute(0, 10, false)).thenReturn(expectedCategories);
         // Act
         List<CategoryDto> result = categoryAllHandler.execute(0, 10, false);

         // Assert
         assertEquals(expectedCategories, result);
     }

     @Test
     void execute_shouldReturnEmptyListWhenNoCategories() {
         // Arrange
         when(categoryFilterService.execute(0, 10, true)).thenReturn(Collections.emptyList());
         // Act
         List<CategoryDto> result = categoryAllHandler.execute(0, 10, true);

         // Assert
         assertEquals(0, result.size());
     }
}
