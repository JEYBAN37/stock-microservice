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
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.service.CategoryListArticle;
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
                new Article(1L,"Article2","dsfd",9,new BigDecimal("10.50"),new Brand(),Arrays.asList(new Category(), new Category())),
                new Article(2l,"Article2","dsfd",9,new BigDecimal("10.50"),new Brand(),Arrays.asList(new Category(), new Category()))
        );

        List<Category> initialCategories = Arrays.asList(
                new Category(1L, "Category1","d"),
                new Category(2L, "Category2","d")
        );

        List<Brand> initialBrands = Arrays.asList(
                new Brand(1L, "Category1","d"),
                new Brand(2L, "Category2","d")
        );
        // Create DAO and Repository with the sample Article list
        CategoryDao categoryDao = new com.example.stock.category.instance.Dao(new ArrayList<>(initialCategories));
        ArticleDao articleDao = new Dao(new ArrayList<>(initialArticles));
        ArticleRepository articleRepository = new Repository(new ArrayList<>(initialArticles));

        // Create the service instance
        CategoryListArticle categoryListArticle = new CategoryListArticle(categoryDao);
        BrandDao brandDao = new com.example.stock.brand.instance.Dao(initialBrands);
        ArticleCreateService articleCreateService = new ArticleCreateService(articleRepository,articleDao,categoryListArticle,brandDao);
        ArticleDtoMapperInstance articleDtoMapper = new ArticleDtoMapperInstance();
        articleCreateHandler = new ArticleCreateHandler(articleCreateService,articleDtoMapper);
    }

    @Test
    void handler_createsArticle_successfully() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article3","dsfd",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleDto createdArticle = articleCreateHandler.execute(command);
        // assert
        assertNotNull(createdArticle);
        assertEquals("ARTICLE3", createdArticle.getName());
    }

    @Test
    void handler_createArticle_whenEmptyName_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"", "description",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenNameTooLong_shouldThrowsArticleException() {
        // arrange
        String longName = "A".repeat(121);
        ArticleCreateCommand command = new ArticleCreateCommand(null,longName, "description",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenEmptyDescription_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"longName", "",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenDescriptionTooLong_shouldThrowsArticleException() {
        // arrange
        String longDescription = "A".repeat(121);
        ArticleCreateCommand command = new ArticleCreateCommand(null,"longName", longDescription,9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Description don't be bigger than 120 characters", exception.getErrorMessage());
    }
    @Test
    void handler_createArticle_whenQuantityLessThanCero_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article", "description", -3, new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Quantity must be greater than zero", exception.getErrorMessage());
    }

    @Test
    void handler_createArticle_whenPriceLessThanZero_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article", "description", 9, new BigDecimal("-0.01"),1L,Arrays.asList(1L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> articleCreateHandler.execute(command));
        // assert
        assertEquals("Price must be greater than zero.", exception.getErrorMessage());
    }

}
