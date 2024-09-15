package com.example.stock.article.articleapplicationtest;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleSuppliesService;
import com.example.stock.domain.brand.model.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleSuppliesServiceTest {

    @Mock
    private ArticleDao articleDao;

    @Mock
    private ArticleRepository articleRepository;

    private ArticleSuppliesService articleSuppliesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articleSuppliesService = new ArticleSuppliesService(articleDao, articleRepository);
    }

    @Test
    void testExecute_WithEmptyList_ShouldThrowException() {
        // Given
        List<ArticleEditCommand> emptyList = Collections.emptyList();

        // When & Then
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(emptyList);
        });
        assertEquals("List Supplies Empty", exception.getErrorMessage());
    }

    @Test
    void testExecute_WithArticleNotFound_ShouldThrowException() {
        // Given
        ArticleEditCommand command = new ArticleEditCommand(1L, 10, new BigDecimal("100.0"));
        List<ArticleEditCommand> commands = Arrays.asList(command);
        when(articleDao.getById(1L)).thenReturn(null);

        // When & Then
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(commands);
        });
        assertEquals("Article No Exist", exception.getErrorMessage());
    }

    @Test
    void testExecute_WithValidCommands_ShouldUpdatdeArticles() {
        // Given
        ArticleEditCommand command = new ArticleEditCommand(1L, 10, new BigDecimal("100.0"));
        List<ArticleEditCommand> commands = Arrays.asList(command);
        Article existingArticle = new Article(1L, "Test Article", "Description", 5, new BigDecimal("100.0"), new Brand(), null);
        Article updatedArticle = new Article(1L, "Test Article", "Description", 10, new BigDecimal("100.0"), new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(existingArticle);
        when(articleRepository.update(any(Article.class))).thenReturn(updatedArticle);

        // When
        List<Article> updatedArticles = articleSuppliesService.execute(commands);

        // Then
        assertEquals(1L, updatedArticles.size());
        assertEquals(10, updatedArticles.get(0).getQuantity());
        assertEquals(new BigDecimal("100.0"), updatedArticles.get(0).getPrice());

        // Verify that the articleRepository.update method was called once
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(articleRepository, times(1)).update(articleCaptor.capture());
        assertEquals(updatedArticle.getId(), articleCaptor.getValue().getId());
    }


    @Test
    void testExecute_WithValidCommands_ShouldUpdateArticles() {
        ArticleEditCommand command = new ArticleEditCommand(1L, 10, new BigDecimal("100.0"));
        List<ArticleEditCommand> commands = Arrays.asList(command);
        Article existingArticle = new Article(1L, "Test Article", "Description", 5, new BigDecimal("100.0"), new Brand(), null);
        Article updatedArticle = new Article(1L, "Test Article", "Description", 10, new BigDecimal("100.0"), new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(existingArticle);
        when(articleRepository.update(any(Article.class))).thenReturn(updatedArticle);

        List<Article> updatedArticles = articleSuppliesService.execute(commands);

        assertEquals(1, updatedArticles.size());
        assertEquals(10, updatedArticles.get(0).getQuantity());
        assertEquals( new BigDecimal("100.0"), updatedArticles.get(0).getPrice());

        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(articleRepository, times(1)).update(articleCaptor.capture());
        assertEquals(updatedArticle.getId(), articleCaptor.getValue().getId());
    }

    // Test de actualización con valores negativos
    @Test
    void testExecute_WithNegativeQuantity_ShouldThrowException() {
        ArticleEditCommand command = new ArticleEditCommand(1L, -10,  new BigDecimal("100.0"));
        List<ArticleEditCommand> commands = Arrays.asList(command);
        Article existingArticle = new Article(1L, "Test Article", "Description", 5,  new BigDecimal("50.0"), new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(existingArticle);

        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(commands);
        });
        assertEquals("Quantity must be greater than zero", exception.getErrorMessage()); // Cambia este mensaje según lo que manejes
    }

    // Test de actualización con valores de precio negativos
    @Test
    void testExecute_WithNegativePrice_ShouldThrowException() {
        ArticleEditCommand command = new ArticleEditCommand(1L, 10,  new BigDecimal(-100.0));
        List<ArticleEditCommand> commands = Arrays.asList(command);
        Article existingArticle = new Article(1L, "Test Article", "Description", 5,  new BigDecimal("50.0"), new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(existingArticle);

        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(commands);
        });
        assertEquals("Price must be greater than zero.", exception.getErrorMessage()); // Cambia este mensaje según lo que manejes
    }

    // Test de actualización de varios artículos
    @Test
    void testExecute_WithMultipleValidCommands_ShouldUpdateAllArticles() {
        ArticleEditCommand command1 = new ArticleEditCommand(1L, 10, new BigDecimal(100.0));
        ArticleEditCommand command2 = new ArticleEditCommand(2L, 5, new BigDecimal(50.0));
        List<ArticleEditCommand> commands = Arrays.asList(command1, command2);

        Article existingArticle1 = new Article(1L, "Test Article 1", "Description", 5, new BigDecimal(50.0), new Brand(), null);
        Article existingArticle2 = new Article(2L, "Test Article 2", "Description", 3, new BigDecimal(30.0), new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(existingArticle1);
        when(articleDao.getById(2L)).thenReturn(existingArticle2);
        when(articleRepository.update(any(Article.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Retornar el artículo actualizado

        List<Article> updatedArticles = articleSuppliesService.execute(commands);

        assertEquals(2, updatedArticles.size());
        assertEquals(10, updatedArticles.get(0).getQuantity());
        assertEquals(new BigDecimal(100.0), updatedArticles.get(0).getPrice());
        assertEquals(5, updatedArticles.get(1).getQuantity());
        assertEquals(new BigDecimal(50.0), updatedArticles.get(1).getPrice());

        verify(articleRepository, times(2)).update(any(Article.class));
    }

    // Test de verificación de llamada al método createUpdatedArticle
    @Test
    void testUpdateArticle_ShouldCallCreateUpdatedArticle() {
        ArticleEditCommand command = new ArticleEditCommand(1L, 10, new BigDecimal(100.0));
        List<ArticleEditCommand> commands = Arrays.asList(command);
        Article existingArticle = new Article(1L, "Test Article", "Description", 5, new BigDecimal(50.0), new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(existingArticle);
        when(articleRepository.update(any(Article.class))).thenReturn(existingArticle);

        articleSuppliesService.execute(commands);

        // Verificar que el método createUpdatedArticle se llamó
        verify(articleRepository, times(1)).update(any(Article.class));
    }
}
