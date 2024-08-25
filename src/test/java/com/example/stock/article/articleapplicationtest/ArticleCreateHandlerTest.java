package com.example.stock.article.articleapplicationtest;



import com.example.stock.application.articule.command.ArticleCreateHandler;
import com.example.stock.article.instance.ArticleDtoMapperInstance;
import com.example.stock.article.instance.Dao;
import com.example.stock.article.instance.Repository;
import com.example.stock.domain.article.model.dto.ArticleDto;
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
class ArticleCreateHandlerTest {
    private ArticleCreateHandler articleCreateHandler;
    @BeforeEach
    public void setUp() {
        // Create DAO and Repository with the sample Article list
        List<Article> initialArticles = Arrays.asList(
                new Article(1L,"Article2","dsfd",9,new BigDecimal("10.50")),
                new Article(2l,"Article2","dsfd",9,new BigDecimal("10.50"))
        );
        // Create DAO and Repository with the sample Article list
        ArticleDao articleDao = new Dao(new ArrayList<>(initialArticles));
        ArticleRepository articleRepository = new Repository(new ArrayList<>(initialArticles));

        // Create the service instance
        ArticleCreateService articleCreateService = new ArticleCreateService(articleRepository,articleDao);
        ArticleDtoMapperInstance articleDtoMapper = new ArticleDtoMapperInstance();
        articleCreateHandler = new ArticleCreateHandler(articleCreateService,articleDtoMapper);
    }

    @Test
    void handler_createsArticle_successfully() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand("Article3","dsfd",9,new BigDecimal("10.50"));
        // act
        ArticleDto createdArticle = articleCreateHandler.execute(command);
        // assert
        assertNotNull(createdArticle);
        assertEquals("Article3", createdArticle.getName());
    }
    @Test
    void handler_createsArticle_whenExistArticle_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand("Article2","dsfd",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Article Exist", exception.getErrorMessage());

    }

    @Test
    void handler_createArticle_whenEmptyName_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand("", "description",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenNameTooLong_shouldThrowsArticleException() {
        // arrange
        String longName = "A".repeat(121);
        ArticleCreateCommand command = new ArticleCreateCommand(longName, "description",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenEmptyDescription_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand("longName", "",9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenDescriptionTooLong_shouldThrowsArticleException() {
        // arrange
        String longDescription = "A".repeat(121);
        ArticleCreateCommand command = new ArticleCreateCommand("longName", longDescription,9,new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Description don't be bigger than 120 characters", exception.getErrorMessage());
    }
    @Test
    void handler_createArticle_whenQuantityLessThanCero_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand("Article", "description", -3, new BigDecimal("10.50"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Quantity must be greater than zero", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenPriceLessThanZero_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand("Article", "description", 9, new BigDecimal("-0.01"));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Price must be greater than zero.", exception.getErrorMessage());
    }

}
