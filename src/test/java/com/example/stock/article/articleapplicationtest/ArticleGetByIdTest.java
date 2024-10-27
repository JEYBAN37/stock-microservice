package com.example.stock.article.articleapplicationtest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.application.articule.query.ArticleGetById;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.service.ArticleGetByIdsService;
import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;
import com.example.stock.domain.category.model.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArticleGetByIdTest {

    @InjectMocks
    private ArticleGetById articleGetById;

    @Mock
    private ArticleGetByIdsService articleGetByIdsService;

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_ValidIds() {
        // Arrange
        List<Long> ids = Arrays.asList(1L, 2L);
        Article article1 =    new Article(1L,"Article2","dsfd",9,new BigDecimal("10.50"),new Brand(),Arrays.asList(new Category(), new Category()));
        Article article2 =    new Article(2L,"Article2","dsfd",9,new BigDecimal("10.50"),new Brand(),Arrays.asList(new Category(), new Category()));
        List<Article> articles = Arrays.asList(article1, article2);
        List<ArticleDto> expectedDtos = Arrays.asList(
                new ArticleDto(1L,"Article2","dsfd",9,new BigDecimal("10.50"),new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle(), new CategoryDtoArticle()}),
                new ArticleDto(2L, "Article2","dsfd",9,new BigDecimal("10.50"),new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle(), new CategoryDtoArticle()})
        );

        when(articleGetByIdsService.execute(ids, 0, 10, true, null, null, null)).thenReturn(articles);
        when(articleDtoMapper.toDto(article1)).thenReturn(expectedDtos.get(0));
        when(articleDtoMapper.toDto(article2)).thenReturn(expectedDtos.get(1));

        // Act
        List<ArticleDto> resultDtos = articleGetById.execute(ids, 0, 10, true, null, null, null);

        // Assert
        assertEquals(expectedDtos, resultDtos);
        verify(articleGetByIdsService).execute(ids, 0, 10, true, null, null, null);
        verify(articleDtoMapper, times(2)).toDto(any(Article.class));
    }

    @Test
    public void testExecute_NullArticles() {
        // Arrange
        List<Long> ids = Arrays.asList(1L, 2L);
        when(articleGetByIdsService.execute(ids, 0, 10, true, null, null, null)).thenReturn(Collections.emptyList());

        // Act
        List<ArticleDto> resultDtos = articleGetById.execute(ids, 0, 10, true, null, null, null);

        // Assert
        assertTrue(resultDtos.isEmpty());
        verify(articleDtoMapper, never()).toDto(any());
    }
}
