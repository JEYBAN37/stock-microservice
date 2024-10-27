package com.example.stock.category.categorydoamintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.service.CategoryListArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class CategoryListArticleTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryListArticle categoryListArticle;

    @BeforeEach
    void setUp() {
        // Configuraciones necesarias
    }

    @Test
    void testExecuteWithValidCategoryList() {
        List<Long> categoryIds = List.of(1L, 2L, 3L);

        // Mock de categorías
        Category category1 = new Category(1L, "Category1","d");
        Category category2 = new Category(2L, "Category2","d");
        Category category3 =new Category(3L, "Category3","d");

        when(categoryDao.getById(1L)).thenReturn(category1);
        when(categoryDao.getById(2L)).thenReturn(category2);
        when(categoryDao.getById(3L)).thenReturn(category3);

        // Ejecutar el método
        List<Category> result = categoryListArticle.execute(categoryIds);

        // Verificar
        verify(categoryDao).getById(1L);
        verify(categoryDao).getById(2L);
        verify(categoryDao).getById(3L);
        assertEquals(3, result.size());
    }

    @Test
    void testExecuteWithNullCategoryList() {
        List<Long> categoryIds = null;
        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryListArticle.execute(categoryIds)
        );
        assertEquals("Category Array Null", exception.getErrorMessage());
    }

    @Test
    void testExecuteWithEmptyCategoryList() {
        List<Long> categoryIds = List.of();
        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryListArticle.execute(categoryIds)
        );
        assertEquals("Category Array Null", exception.getErrorMessage());
    }

    @Test
    void testExecuteWithExceedingCategoryListSize() {
        List<Long> categoryIds = List.of(1L, 2L, 3L, 4L);
        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryListArticle.execute(categoryIds)
        );
        assertEquals("size invalid", exception.getErrorMessage());
    }

    @Test
    void testExecuteWithDuplicateCategoryIds() {
        List<Long> categoryIds = List.of(1L, 2L, 1L);

        Category category1 = new Category(1L, "Category1","d");
        Category category2 = new Category(2L, "Category1","d");

        when(categoryDao.getById(1L)).thenReturn(category1);
        when(categoryDao.getById(2L)).thenReturn(category2);

        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryListArticle.execute(categoryIds)
        );
        assertEquals("Category duplicated: 1", exception.getErrorMessage());
    }

    @Test
    void testExecuteWithInvalidCategoryId() {
        List<Long> categoryIds = List.of(-1L, 2L);

        ArticleException exception = assertThrows(ArticleException.class, () ->
                categoryListArticle.execute(categoryIds)
        );
        assertEquals("Category Not category valid -1", exception.getErrorMessage());
    }

    @Test
    void testExecuteWithNonExistingCategory() {
        List<Long> categoryIds = List.of(1L, 2L);

        when(categoryDao.getById(1L)).thenReturn(null);

        CategoryException exception = assertThrows(CategoryException.class, () ->
                categoryListArticle.execute(categoryIds)
        );
        assertEquals("Category not found 1", exception.getErrorMessage());
    }
}
