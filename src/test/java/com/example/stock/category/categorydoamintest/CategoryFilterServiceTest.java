package com.example.stock.category.categorydoamintest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.service.CategoryFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryFilterServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryFilterService categoryFilterService;

    @BeforeEach
    void setUp() {
        // Configuraciones de setup si fueran necesarias
    }

    @Test
    void testExecuteWithValidParametersAscendingTrue() {
        int page = 1;
        int size = 10;
        boolean ascending = true;

        // Configuración del comportamiento del mock
        List<Category> mockCategories = List.of(new Category(), new Category());
        when(categoryDao.getAll(page, size, ascending)).thenReturn(mockCategories);

        // Ejecución del método
        List<Category> result = categoryFilterService.execute(page, size, ascending);

        // Verificar que se llamó a getAll con los parámetros esperados
        verify(categoryDao).getAll(page, size, ascending);

        // Comprobar el resultado
        assertEquals(mockCategories.size(), result.size());
    }

    @Test
    void testExecuteWithValidParametersAscendingFalse() {
        int page = 0;
        int size = 5;
        boolean ascending = false;

        // Configuración del comportamiento del mock
        List<Category> mockCategories = List.of(new Category(), new Category(), new Category());
        when(categoryDao.getAll(page, size, ascending)).thenReturn(mockCategories);

        // Ejecución del método
        List<Category> result = categoryFilterService.execute(page, size, ascending);

        // Verificar que se llamó a getAll con los parámetros esperados
        verify(categoryDao).getAll(page, size, ascending);

        // Comprobar el resultado
        assertEquals(mockCategories.size(), result.size());
    }

    @Test
    void testExecuteWithInvalidPage() {
        int page = -1;
        int size = 10;
        boolean ascending = true;

        // Verificar que se lance la excepción ArticleException con un mensaje específico
        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryFilterService.execute(page, size, ascending)
        );
        assertEquals("Page index must be non-negative and size must be greater than zero.", exception.getErrorMessage());

        // Verificar que no se llame a getAll cuando hay parámetros inválidos
        verify(categoryDao, never()).getAll(anyInt(), anyInt(), anyBoolean());
    }

    @Test
    void testExecuteWithInvalidSize() {
        int page = 1;
        int size = 0;
        boolean ascending = true;

        // Verificar que se lance la excepción ArticleException con un mensaje específico
        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryFilterService.execute(page, size, ascending)
        );
        assertEquals("Page index must be non-negative and size must be greater than zero.", exception.getErrorMessage());

        // Verificar que no se llame a getAll cuando hay parámetros inválidos
        verify(categoryDao, never()).getAll(anyInt(), anyInt(), anyBoolean());
    }
}
