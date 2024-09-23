package com.example.stock.category.categoryinfrasatructuretest;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import com.example.stock.infrastructure.category.adapter.jpa.CategorySpringJpaAdapterRepository;
import com.example.stock.infrastructure.category.adapter.jpa.dao.CategoryH2Dao;
import com.example.stock.infrastructure.category.adapter.mapper.CategoryDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryH2DaoTest {

    @Mock
    private CategorySpringJpaAdapterRepository categorySpringJpaAdapterRepository;

    @Mock
    private CategoryDboMapper categoryDboMapper;

    @InjectMocks
    private CategoryH2Dao categoryH2Dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getByName_ShouldReturnCategory_WhenFound() {
        // Arrange
        String name = "CategoryName";
        CategoryEntity categoryEntity = new CategoryEntity(); // Popula con los campos necesarios
        Category expectedCategory = new Category(); // Popula con los campos necesarios

        when(categorySpringJpaAdapterRepository.findByName(name)).thenReturn(categoryEntity);
        when(categoryDboMapper.toDomain(categoryEntity)).thenReturn(expectedCategory);

        // Act
        Category actualCategory = categoryH2Dao.getByName(name);

        // Assert
        assertEquals(expectedCategory, actualCategory);
        verify(categorySpringJpaAdapterRepository, times(1)).findByName(name);
    }

    @Test
    public void getById_ShouldReturnCategory_WhenFound() {
        // Arrange
        Long id = 1L;
        CategoryEntity categoryEntity = new CategoryEntity(); // Popula con los campos necesarios
        Category expectedCategory = new Category(); // Popula con los campos necesarios

        when(categorySpringJpaAdapterRepository.findById(id)).thenReturn(Optional.of(categoryEntity));
        when(categoryDboMapper.toDomain(categoryEntity)).thenReturn(expectedCategory);

        // Act
        Category actualCategory = categoryH2Dao.getById(id);

        // Assert
        assertEquals(expectedCategory, actualCategory);
        verify(categorySpringJpaAdapterRepository, times(1)).findById(id);
    }

    @Test
    public void nameExist_ShouldReturnTrue_WhenNameExists() {
        // Arrange
        String name = "ExistingName";
        when(categorySpringJpaAdapterRepository.existsByName(name)).thenReturn(true);

        // Act
        boolean exists = categoryH2Dao.nameExist(name);

        // Assert
        assertTrue(exists);
        verify(categorySpringJpaAdapterRepository, times(1)).existsByName(name);
    }

    @Test
    public void idExist_ShouldReturnTrue_WhenIdExists() {
        // Arrange
        Long id = 1L;
        when(categorySpringJpaAdapterRepository.existsById(id)).thenReturn(true);

        // Act
        boolean exists = categoryH2Dao.idExist(id);

        // Assert
        assertTrue(exists);
        verify(categorySpringJpaAdapterRepository, times(1)).existsById(id);
    }

    @Test
    public void getAll_ShouldReturnListOfCategories() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;
        CategoryEntity categoryEntity1 = new CategoryEntity(); // Popula con los campos necesarios
        CategoryEntity categoryEntity2 = new CategoryEntity(); // Popula con los campos necesarios
        List<CategoryEntity> categoryEntities = Arrays.asList(categoryEntity1, categoryEntity2);
        Category category1 = new Category(); // Popula con los campos necesarios
        Category category2 = new Category(); // Popula con los campos necesarios

        when(categorySpringJpaAdapterRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(categoryEntities));
        when(categoryDboMapper.toDomain(categoryEntity1)).thenReturn(category1);
        when(categoryDboMapper.toDomain(categoryEntity2)).thenReturn(category2);

        // Act
        List<Category> actualCategories = categoryH2Dao.getAll(page, size, ascending);

        // Assert
        assertEquals(2, actualCategories.size());
        assertEquals(category1, actualCategories.get(0));
        assertEquals(category2, actualCategories.get(1));
        verify(categorySpringJpaAdapterRepository, times(1)).findAll(any(Pageable.class));
    }
}
