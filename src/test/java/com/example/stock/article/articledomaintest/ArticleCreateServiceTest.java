package com.example.stock.article.articledomaintest;

import com.example.stock.article.instance.Dao;
import com.example.stock.article.instance.Repository;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArticleCreateServiceTest {
    private ArticleCreateService articleCreateService;

    @BeforeEach
    public void setUp() {
        List<Article> initialArticle = Arrays.asList(
                new Article(1L, "Article1","d",9,new BigDecimal("10.50")),
                new Article(2L, "Article2","d",9,new BigDecimal("10.50"))
        );

        // Create DAO and Repository with the sample Article list
        ArticleDao articleDao = new Dao(new ArrayList<>(initialArticle));
        ArticleRepository articleRepository = new Repository(new ArrayList<>(initialArticle));

        // Create the service instance
        articleCreateService = new ArticleCreateService(articleRepository, articleDao);
    }
    @Test
     void createsArticle_successfully() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand("Article3","dsfd",9,new BigDecimal("10.50"));
        // act
        Article createdArticle = articleCreateService.execute(command);
        // assert
        assertNotNull(createdArticle);
        assertEquals("Article3", createdArticle.getName());
        assertEquals(3L, createdArticle.getId()); // Assuming auto-increment logic

    }
    @Test
    void createArticle_whenExistArticle_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand("Article2","dsfd",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("Article Exist", exception.getErrorMessage());

    }
    @Test
    void createArticle_whenEmptyName_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand("", "description",9,new BigDecimal("10.50"));
        //act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        //assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }
    @Test
    void createArticle_whenNameTooLong_shouldThrowsArticleException() {
        // arrange
        String longName = "A".repeat(51);
        ArticleCreateCommand command = new ArticleCreateCommand(longName, "description",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }
    @Test
    void createArticle_whenEmptyDescription_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand("longName", "",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        //assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }
    @Test
    void createArticle_whenDescriptionTooLong_shouldThrowsArticleException() {
        //Arrange
        String longDescription = "A".repeat(91);
        ArticleCreateCommand command = new ArticleCreateCommand("longName", longDescription,9,new BigDecimal("10.50"));

      //Act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        //Assert
        assertEquals("Description don't be bigger than 120 characters", exception.getErrorMessage());
    }

    @Test
    void createArticle_whenQuantityIsNegative_shouldThrowsArticleException() {
        // Arrange
        ArticleCreateCommand command = new ArticleCreateCommand("ValidName", "Valid description", -5, new BigDecimal("10.50"));

        // Act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });

        // Assert
        assertEquals("Quantity must be greater than zero", exception.getErrorMessage());
    }

    @Test
    void createArticle_whenPriceIsZero_shouldThrowsArticleException() {
        // Arrange
        ArticleCreateCommand command = new ArticleCreateCommand("ValidName", "Valid description", 5, BigDecimal.ZERO);

        // Act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });

        // Assert
        assertEquals("Price must be greater than zero.", exception.getErrorMessage());
    }

    @Test
    void createArticle_whenPriceIsNegative_shouldThrowsArticleException() {
        // Arrange
        ArticleCreateCommand command = new ArticleCreateCommand("ValidName", "Valid description", 5, new BigDecimal("-10.50"));

        // Act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });

        // Assert
        assertEquals("Price must be greater than zero.", exception.getErrorMessage());
    }

}
