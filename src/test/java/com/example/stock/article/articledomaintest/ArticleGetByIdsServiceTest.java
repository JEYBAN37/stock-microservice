package com.example.stock.article.articledomaintest;

import static com.example.stock.domain.static_variables.StaticData.MESSAGE_PAGE_VALID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.service.ArticleGetByIdsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArticleGetByIdsServiceTest {

    @InjectMocks
    private ArticleGetByIdsService articleGetByIdsService;

    @Mock
    private ArticleDao articleDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_ValidInput() {
        // Arrange
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        int page = 0;
        int size = 10;
        boolean ascending = true;
        String byName = null;
        String byBrand = null;
        String byCategory = null;

        List<Article> expectedArticles = Arrays.asList(new Article(), new Article());
        when(articleDao.getAllByIds(ids, page, size, ascending, byName, byBrand, byCategory))
                .thenReturn(expectedArticles);

        // Act
        List<Article> articles = articleGetByIdsService.execute(ids, page, size, ascending, byName, byBrand, byCategory);

        // Assert
        assertEquals(expectedArticles.size(), articles.size());
        verify(articleDao).getAllByIds(ids, page, size, ascending, byName, byBrand, byCategory);
    }

    @Test
    public void testExecute_EmptyIds() {
        // Arrange
        List<Long> ids = Collections.emptyList();
        int page = 0;
        int size = 10;
        boolean ascending = true;
        String byName = null;
        String byBrand = null;
        String byCategory = null;

        // Act
        List<Article> articles = articleGetByIdsService.execute(ids, page, size, ascending, byName, byBrand, byCategory);

        // Assert
        assertTrue(articles.isEmpty());
        verify(articleDao, never()).getAllByIds(any(), anyInt(), anyInt(), anyBoolean(), any(), any(), any());
    }

    @Test
    public void testExecute_InvalidPage() {
        // Arrange
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        int page = -1; // Invalid page
        int size = 10;
        boolean ascending = true;
        String byName = null;
        String byBrand = null;
        String byCategory = null;

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleGetByIdsService.execute(ids, page, size, ascending, byName, byBrand, byCategory);
        });

        assertEquals(MESSAGE_PAGE_VALID, exception.getErrorMessage());
    }

    @Test
    public void testExecute_InvalidSize() {
        // Arrange
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        int page = 0;
        int size = 0; // Invalid size
        boolean ascending = true;
        String byName = null;
        String byBrand = null;
        String byCategory = null;

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleGetByIdsService.execute(ids, page, size, ascending, byName, byBrand, byCategory);
        });

        assertEquals(MESSAGE_PAGE_VALID, exception.getErrorMessage());
    }
}
