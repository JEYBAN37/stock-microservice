package com.example.stock.article.articledomaintest;

import com.example.stock.brand.instance.Dao;
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
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleCreateServiceTest {


    private ArticleDao articleDao;

    private ArticleRepository articleRepository;

    private CategoryDao categoryDao;
    private BrandDao brandDao;

    private ArticleCreateService articleCreateService;
    private CategoryListArticle categoryListArticle;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);


        List<Article> initialArticles = Arrays.asList(
                new Article(1L, "Article1", "d", 9, new BigDecimal("10.50"),new Brand(),
                        Arrays.asList(new Category(1L, "Category1", "d"), new Category(2L, "Category2", "d"))),
                new Article(2L, "Article2", "d", 9, new BigDecimal("10.50"),new Brand(),
                        Arrays.asList(new Category(1L, "Category1", "d"), new Category(2L, "Category2", "d")))
        );

        List<Category> initialCategories = Arrays.asList(
                new Category(1L, "Category1", "d"),
                new Category(2L, "Category2", "d")
        );

        List<Brand> initialBrands = Arrays.asList(
                new Brand(1L, "Category1","d"),
                new Brand(2L, "Category2","d")
        );
        categoryDao = new com.example.stock.category.instance.Dao(new ArrayList<>(initialCategories));
        articleDao = new com.example.stock.article.instance.Dao(new ArrayList<>(initialArticles));


        articleRepository = new com.example.stock.article.instance.Repository(new ArrayList<>(initialArticles));
        brandDao  = new Dao(new ArrayList<>(initialBrands));

        categoryListArticle = new CategoryListArticle(categoryDao);
        articleCreateService = new ArticleCreateService(articleRepository, articleDao, categoryListArticle,brandDao);
    }



@Test
     void createsArticle_successfully() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article3","dsfd",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        Article createdArticle = articleCreateService.execute(command);
        // assert
        assertNotNull(createdArticle);
        assertEquals("ARTICLE3", createdArticle.getName());
        assertEquals(3L, createdArticle.getId()); // Assuming auto-increment logic

    }

    @Test
    void createArticle_whenIdPresent_successfully() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(5L,"Article8","dsfd",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
        // act
        Article createdArticle = articleCreateService.execute(command);
        // assert
        assertNotNull(createdArticle);
        assertEquals("ARTICLE8", createdArticle.getName());
        assertEquals(3L, createdArticle.getId());

    }


    @Test
    void createArticle_whenRepeatCategory_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article8","dsfd",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L, 2L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("Category duplicated: 2", exception.getErrorMessage());

    }

    @Test
    void createArticle_whenInvalidArrayCategory_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article8","dsfd",9,new BigDecimal("10.50"),1L,Arrays.asList(1L,2L,2L,5L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("size invalid", exception.getErrorMessage());

    }

    @Test
    void createArticle_whenInvalidBrand_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article8","dsfd",9,new BigDecimal("10.50"),10L,Arrays.asList(1L,2L,2L,5L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("Brand Inject Not Found", exception.getErrorMessage());
    }

    @Test
    void createArticle_whenNullBrand_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article8","dsfd",9,new BigDecimal("10.50"),null,Arrays.asList(1L,2L,2L,5L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("Brand Not Found", exception.getErrorMessage());
    }

    @Test
    void createArticle_whenNullArrayCategory_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article8","dsfd",9,new BigDecimal("10.50"),1L,null);
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("Category Array Null", exception.getErrorMessage());

    }

    @Test
    void createArticle_whenEmptyArrayCategory_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"Article8","dsfd",9,new BigDecimal("10.50"),1L,Arrays.asList(1L,2L,2L,5L));
        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });
        // assert
        assertEquals("size invalid", exception.getErrorMessage());

    }

    @Test
    void createArticle_whenEmptyName_shouldThrowsArticleException() {
        //arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"", "description",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
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
        ArticleCreateCommand command = new ArticleCreateCommand(null,longName, "description",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
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
        ArticleCreateCommand command = new ArticleCreateCommand(null,"longName", "",9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));
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
        String longDescription = "A".repeat(124);
        ArticleCreateCommand command = new ArticleCreateCommand(null,"longName", longDescription,9,new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));

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
        ArticleCreateCommand command = new ArticleCreateCommand(null,"ValidName", "Valid description", -5, new BigDecimal("10.50"),1L,Arrays.asList(1L, 2L));

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
        ArticleCreateCommand command = new ArticleCreateCommand(null,"ValidName", "Valid description", 5, BigDecimal.ZERO,1L,Arrays.asList(1L, 2L));

        // Act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });

        // Assert
        assertEquals("Price must be greater than zero.", exception.getErrorMessage());
    }

    @Test
    void createArticle_whenPriceIsNegative_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand command = new ArticleCreateCommand(null,"ValidName", "Valid description", 5, new BigDecimal("-10.50"),1L,Arrays.asList(1L, 2L));

        // act
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleCreateService.execute(command);
        });

        // assert
        assertEquals("Price must be greater than zero.", exception.getErrorMessage());
    }

}
