package com.example.stock.categoryapplicationtest;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.application.category.query.CategoryAllHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void execute_shouldReturnListOfCategoryDtos() {
        // arrange
        Category category1 = new Category(1L, "Category 1", "Description 1");
        Category category2 = new Category(2L, "Category 2", "Description 2");

        CategoryDto categoryDto1 = new CategoryDto(1L, "Category 1", "Description 1");
        CategoryDto categoryDto2 = new CategoryDto(2L, "Category 2", "Description 2");

        List<Category> mockCategories = List.of(category1, category2);
        List<CategoryDto> expectedDtos = List.of(categoryDto1, categoryDto2);

        when(categoryDao.getAll(0, 10, true)).thenReturn(mockCategories);
        when(categoryDtoMapper.toDto(category1)).thenReturn(categoryDto1);
        when(categoryDtoMapper.toDto(category2)).thenReturn(categoryDto2);
        // act
        List<CategoryDto> result = categoryAllHandler.execute(0, 10, true);
        // assert
        assertEquals(expectedDtos, result);
    }

    @Test
    void execute_shouldReturnCategoriesInDescendingOrder() {
        // arrange
        Category category1 = new Category(1L, "Category A", "Description A");
        Category category2 = new Category(2L, "Category B", "Description B");

        CategoryDto categoryDto1 = new CategoryDto(1L, "Category A", "Description A");
        CategoryDto categoryDto2 = new CategoryDto(2L, "Category B", "Description B");

        List<Category> mockCategories = List.of(category2, category1); // Lista en orden original
        List<CategoryDto> expectedDtos = List.of(categoryDto2, categoryDto1); // Esperamos en orden descendente

        when(categoryDao.getAll(0, 10, false)).thenReturn(mockCategories);
        when(categoryDtoMapper.toDto(category1)).thenReturn(categoryDto1);
        when(categoryDtoMapper.toDto(category2)).thenReturn(categoryDto2);
        // act
        List<CategoryDto> result = categoryAllHandler.execute(0, 10, false);
        // assert
        assertEquals(expectedDtos, result); // Debe retornar la lista en orden descendente
    }
    @Test
    void execute_shouldReturnEmptyListWhenNoCategories() {
        // arrange
        List<Category> mockCategories = List.of();
        when(categoryDao.getAll(0, 10, true)).thenReturn(mockCategories);
        // act
        List<CategoryDto> result = categoryAllHandler.execute(0, 10, false);
        // assert
        assertEquals(0, result.size());
    }
}
