package com.example.stock.article.articledomaintest;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.article.model.exception.ArticleException;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryFilterServiceTest {
 @Mock
private CategoryDao categoryDao;

@Mock
private CategoryDtoMapper categoryDtoMapper;

@InjectMocks
private CategoryFilterService categoryFilterService;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}



@Test
void execute_InvalidPage_ThrowsException() {
    // Arrange
    int invalidPage = -1;
    int size = 5;

    // Act & Assert
    ArticleException exception = assertThrows(ArticleException.class, () -> {
        categoryFilterService.execute(invalidPage, size, true);
    });

    assertEquals("Page index must be non-negative and size must be greater than zero.", exception.getErrorMessage());
}

@Test
void execute_InvalidSize_ThrowsException() {
    // Arrange
    int page = 0;
    int invalidSize = 0;

    // Act & Assert
    ArticleException exception = assertThrows(ArticleException.class, () -> {
        categoryFilterService.execute(page, invalidSize, true);
    });

    assertEquals("Page index must be non-negative and size must be greater than zero.", exception.getErrorMessage());
}
}
