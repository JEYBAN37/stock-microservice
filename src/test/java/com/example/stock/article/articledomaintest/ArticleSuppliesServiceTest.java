package com.example.stock.article.articledomaintest;

import com.example.stock.domain.article.model.dto.command.ArticleSupplyCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleSuppliesService;
import com.example.stock.domain.brand.model.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ArticleSuppliesServiceTest {

    @Mock
    private ArticleDao articleDao;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleSuppliesService articleSuppliesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_NullIdOrQuantityZero_ThrowsArticleException() {
        // Arrange
        ArticleSupplyCommand commandWithNullId = new ArticleSupplyCommand(null, 0, new BigDecimal(100.0));
        ArticleSupplyCommand commandWithZeroQuantity = new ArticleSupplyCommand(1L, 0, new BigDecimal(100.0));

        // Act & Assert
        ArticleException exception1 = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(commandWithNullId);
        });
        assertEquals("Supplies Empty", exception1.getErrorMessage());

        ArticleException exception2 = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(commandWithZeroQuantity);
        });
        assertEquals("Supplies Empty", exception2.getErrorMessage());
    }

    @Test
    void execute_ArticleNotFound_ThrowsArticleException() {
        // Arrange
        ArticleSupplyCommand command = new ArticleSupplyCommand(1L, 5, new BigDecimal(100.0));
        when(articleDao.getById(1L)).thenReturn(null);

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(command);
        });

        assertEquals("Article No Exist", exception.getErrorMessage());
    }

    @Test
    void execute_InvalidQuantity_ThrowsArticleException() {
        // Arrange
        ArticleSupplyCommand command = new ArticleSupplyCommand(1L, -1, new BigDecimal(100.0));
        Article foundArticle = new Article(1L, "Article 1", "Description", 10, new BigDecimal(150.0),  new Brand(), null);
        when(articleDao.getById(1L)).thenReturn(foundArticle);

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleSuppliesService.execute(command);
        });

        assertEquals("Quantity invalid", exception.getErrorMessage());
    }

    @Test
    void execute_ValidCommand_UpdatesArticle() {
        // Arrange
        ArticleSupplyCommand command = new ArticleSupplyCommand(1L, 5, new BigDecimal(200.0));
        Article foundArticle = new Article(1L, "Article 1", "Description", 10, new BigDecimal(150.0),  new Brand(), null);

        when(articleDao.getById(1L)).thenReturn(foundArticle);

        // Act
        articleSuppliesService.execute(command);

        // Assert
        verify(articleRepository, times(1)).update(argThat(updatedArticle ->
                updatedArticle.getId().equals(foundArticle.getId()) &&
                        updatedArticle.getQuantity() == foundArticle.getQuantity() + 5 &&
                        updatedArticle.getPrice().equals(command.getPrice())
        ));
    }

}
