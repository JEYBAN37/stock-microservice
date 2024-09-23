package com.example.stock.category.categoryinfrasatructuretest;

import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import com.example.stock.infrastructure.category.adapter.jpa.CategorySpringJpaAdapterRepository;
import com.example.stock.infrastructure.category.adapter.jpa.respository.CategoryH2Repository;
import com.example.stock.infrastructure.category.adapter.mapper.CategoryDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryH2RepositoryTest {

    @Mock
    private CategorySpringJpaAdapterRepository categorySpringJpaAdapterRepository;

    @Mock
    private CategoryDboMapper categoryDboMapper;

    @InjectMocks
    private CategoryH2Repository categoryH2Repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void create_ShouldReturnCategory_WhenSavedSuccessfully() {
        // Arrange
        Category requestCategory = new Category(); // Popula con los campos necesarios
        CategoryEntity categoryEntityToSave = new CategoryEntity(); // Popula con los campos necesarios
        CategoryEntity categoryEntitySaved = new CategoryEntity(); // Popula con los campos necesarios
        Category expectedCategory = new Category(); // Popula con los campos necesarios

        when(categoryDboMapper.toDatabase(requestCategory)).thenReturn(categoryEntityToSave);
        when(categorySpringJpaAdapterRepository.save(categoryEntityToSave)).thenReturn(categoryEntitySaved);
        when(categoryDboMapper.toDomain(categoryEntitySaved)).thenReturn(expectedCategory);

        // Act
        Category actualCategory = categoryH2Repository.create(requestCategory);

        // Assert
        assertEquals(expectedCategory, actualCategory);
        verify(categoryDboMapper, times(1)).toDatabase(requestCategory);
        verify(categorySpringJpaAdapterRepository, times(1)).save(categoryEntityToSave);
        verify(categoryDboMapper, times(1)).toDomain(categoryEntitySaved);
    }

    @Test
     void deleteById_ShouldInvokeDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        categoryH2Repository.deleteById(id);

        // Assert
        verify(categorySpringJpaAdapterRepository, times(1)).deleteById(id);
    }

    @Test
     void update_ShouldReturnUpdatedCategory_WhenUpdatedSuccessfully() {
        // Arrange
        Category requestCategory = new Category(); // Popula con los campos necesarios
        CategoryEntity categoryEntityToUpdate = new CategoryEntity(); // Popula con los campos necesarios
        CategoryEntity categoryEntityUpdated = new CategoryEntity(); // Popula con los campos necesarios
        Category expectedCategory = new Category(); // Popula con los campos necesarios

        when(categoryDboMapper.toDatabase(requestCategory)).thenReturn(categoryEntityToUpdate);
        when(categorySpringJpaAdapterRepository.save(categoryEntityToUpdate)).thenReturn(categoryEntityUpdated);
        when(categoryDboMapper.toDomain(categoryEntityUpdated)).thenReturn(expectedCategory);

        // Act
        Category actualCategory = categoryH2Repository.update(requestCategory);

        // Assert
        assertEquals(expectedCategory, actualCategory);
        verify(categoryDboMapper, times(1)).toDatabase(requestCategory);
        verify(categorySpringJpaAdapterRepository, times(1)).save(categoryEntityToUpdate);
        verify(categoryDboMapper, times(1)).toDomain(categoryEntityUpdated);
    }
}
