package com.example.stock.category.categoryapplicationtest;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.application.category.query.CategoryByName;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryByNameTest {

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @InjectMocks
    private CategoryByName categoryByName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        String categoryName = "Electronics";
        Category category = new Category(1L, "Electronics", "Category for electronics items");
        CategoryDto expectedDto = new CategoryDto("Electronics", "Category for electronics items");

        when(categoryDao.getByName(categoryName)).thenReturn(category);
        when(categoryDtoMapper.toDto(category)).thenReturn(expectedDto);

        // Act
        CategoryDto result = categoryByName.execute(categoryName);

        // Assert
        verify(categoryDao, times(1)).getByName(categoryName);
        verify(categoryDtoMapper, times(1)).toDto(category);
        assertEquals(expectedDto, result);
    }
}
