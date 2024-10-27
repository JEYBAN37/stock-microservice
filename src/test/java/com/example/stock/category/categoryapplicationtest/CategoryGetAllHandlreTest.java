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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
     void execute_shouldReturnEmptyListWhenNoCategories() {
         // Arrange
         when(categoryFilterService.execute(0, 10, true)).thenReturn(Collections.emptyList());
         // Act
         List<CategoryDto> result = categoryAllHandler.execute(0, 10, true);

         // Assert
         assertEquals(0, result.size());
     }

     @Test
     void testExecuteWithDefaultValues() {
         // Valores de entrada
         Integer page = null;
         Integer size = null;
         Boolean ascending = null;

         // Configurar el comportamiento del servicio simulado
         List<Category> mockCategories = List.of(new Category(), new Category());
         when(categoryFilterService.execute(anyInt(), anyInt(), anyBoolean())).thenReturn(mockCategories);

         // Configurar el comportamiento del mapper
         CategoryDto mockDto = new CategoryDto();
         when(categoryDtoMapper.toDto(any())).thenReturn(mockDto);

         // Ejecutar el método
         List<CategoryDto> result = categoryAllHandler.execute(page, size, ascending);

         // Verificar que el servicio fue llamado con los valores predeterminados
         verify(categoryFilterService).execute(0, 10, false);

         // Verificar que el mapper fue llamado para cada categoría
         verify(categoryDtoMapper, times(mockCategories.size())).toDto(any(Category.class));

         // Comprobar el resultado
         assertEquals(mockCategories.size(), result.size());
     }

     @Test
     void testExecuteWithCustomValues() {
         // Valores de entrada personalizados
         Integer page = 1;
         Integer size = 5;
         Boolean ascending = true;

         // Configurar el comportamiento del servicio simulado
         List<Category> mockCategories = List.of(new Category(), new Category(), new Category());
         when(categoryFilterService.execute(page, size, ascending)).thenReturn(mockCategories);

         // Configurar el comportamiento del mapper
         CategoryDto mockDto = new CategoryDto();
         when(categoryDtoMapper.toDto(any())).thenReturn(mockDto);

         // Ejecutar el método
         List<CategoryDto> result = categoryAllHandler.execute(page, size, ascending);

         // Verificar que el servicio fue llamado con los valores personalizados
         verify(categoryFilterService).execute(page, size, ascending);

         // Verificar que el mapper fue llamado para cada categoría
         verify(categoryDtoMapper, times(mockCategories.size())).toDto(any(Category.class));

         // Comprobar el resultado
         assertEquals(mockCategories.size(), result.size());
     }

 }
