package com.example.stock.article.articledomaintest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.stock.domain.article.model.dto.command.ArticleSaleCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleSaleService;
import com.example.stock.domain.brand.model.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArticleSaleServiceTest {

    @InjectMocks
    private ArticleSaleService articleSaleService;

    @Mock
    private ArticleDao articleDao;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testExecute_ValidSales() {
        // Arrange
        Article article1 = new Article(2L, "Article 2", "Brand 2",3, new BigDecimal(8),new Brand(), Collections.emptyList());
        Article article2 = new Article(1L, "Article 2", "Brand 2",10, new BigDecimal(8),new Brand(), Collections.emptyList());
        List<ArticleSaleCommand> saleCommands = Arrays.asList(
                new ArticleSaleCommand(1L, 2), // Buy 2 of Article 1
                new ArticleSaleCommand(2L, 3)  // Buy 3 of Article 2
        );

        when(articleDao.getAllBySales(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(article1, article2));
        when(articleRepository.update(any(Article.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        List<Article> updatedArticles = articleSaleService.execute(saleCommands);

        // Assert
        assertEquals(2, updatedArticles.size());
        verify(articleRepository, times(2)).update(any(Article.class));
    }

    @Test
    public void testExecute_EmptyCommands() {
        // Arrange
        List<ArticleSaleCommand> saleCommands = Collections.emptyList();

        // Act
        List<Article> updatedArticles = articleSaleService.execute(saleCommands);

        // Assert
        assertTrue(updatedArticles.isEmpty());
        verify(articleDao, never()).getAllBySales(any());
        verify(articleRepository, never()).update(any(Article.class));
    }

    @Test
    public void testExecute_NoSufficientQuantity() {
        // Arrange
        Article article =  new Article(2L, "Article 2", "Brand 2",3, new BigDecimal(8),new Brand(), Collections.emptyList());
        List<ArticleSaleCommand> saleCommands = Collections.singletonList(
                new ArticleSaleCommand(1L, 2) // Trying to buy 2, but only 1 available
        );

        when(articleDao.getAllBySales(Collections.singletonList(1L))).thenReturn(Collections.singletonList(article));

        // Act
        List<Article> updatedArticles = articleSaleService.execute(saleCommands);

        // Assert
        assertTrue(updatedArticles.isEmpty());
        verify(articleRepository, never()).update(any(Article.class));
    }

    @Test
    public void testExecute_ArticleNotFound() {
        // Arrange
        List<ArticleSaleCommand> saleCommands = Collections.singletonList(
                new ArticleSaleCommand(1L, 2) // Trying to buy 2 of a non-existent article
        );

        when(articleDao.getAllBySales(Collections.singletonList(1L))).thenReturn(Collections.emptyList());

        // Act
        List<Article> updatedArticles = articleSaleService.execute(saleCommands);

        // Assert
        assertTrue(updatedArticles.isEmpty());
        verify(articleRepository, never()).update(any(Article.class));
    }
}
