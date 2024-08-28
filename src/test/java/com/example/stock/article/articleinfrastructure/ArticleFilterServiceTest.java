package com.example.stock.article.articleinfrastructure;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.service.ArticleFilterService;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ArticleFilterServiceTest {
    private ArticleFilterService articleFilterService;
    private ArticleDao articleDao;
    private ArticleDtoMapper articleDtoMapper;

    @BeforeEach
    public void setUp() {
        articleDao = mock(ArticleDao.class);
        articleDtoMapper = mock(ArticleDtoMapper.class);
        articleFilterService = new ArticleFilterService(articleDtoMapper, articleDao);
    }

    @Test
    public void testExecute() {
        // arrange
        int page = 0;
        int size = 10;
        boolean ascending = false;
        String byName = "TestName";
        String byBrand = "TestBrand";
        String byCategory = "TestCategory";

        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L, "Article1", "d", 9, new BigDecimal("10.50"), new Brand(), new Category[] {new Category()}));
        articles.add(new Article(2L, "Article2", "d", 9, new BigDecimal("10.50"), new Brand(), new Category[] {new Category()}));

        when(articleDao.getAll(page, size, ascending, byName, byBrand, byCategory)).thenReturn(articles);

        List<ArticleDto> expectedDtos = new ArrayList<>();
        expectedDtos.add(new ArticleDto("Article1", "d", 9, new BigDecimal("10.50"), new Brand(), new Category[] {new Category()}));
        expectedDtos.add(new ArticleDto("Article2", "d", 9, new BigDecimal("10.50"), new Brand(), new Category[] {new Category()}));

        when(articleDtoMapper.toDto(any(Article.class))).thenAnswer(invocation -> {
            Article article = invocation.getArgument(0);
            return new ArticleDto(article.getName(), article.getDescription(), article.getQuantity(), article.getPrice(), article.getBrand(), article.getArticleCategories());
        });

        // Act
        List<ArticleDto> actualDtos = articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory);

        // Assert
        assertEquals(expectedDtos.size(), actualDtos.size());
        for (int i = 0; i < expectedDtos.size(); i++) {
            assertEquals(expectedDtos.get(i).getName(), actualDtos.get(i).getName());
            assertEquals(expectedDtos.get(i).getDescription(), actualDtos.get(i).getDescription()); // Asegúrate de comparar todos los atributos
            assertEquals(expectedDtos.get(i).getQuantity(), actualDtos.get(i).getQuantity());
            assertEquals(expectedDtos.get(i).getPrice(), actualDtos.get(i).getPrice());
            assertEquals(expectedDtos.get(i).getBrand(), actualDtos.get(i).getBrand());
            assertArrayEquals(expectedDtos.get(i).getArticleCategories(), actualDtos.get(i).getArticleCategories());
        }

        // Verify interactions
        verify(articleDao).getAll(page, size, ascending, byName, byBrand, byCategory);
        verify(articleDtoMapper, times(2)).toDto(any(Article.class));
    }

}
