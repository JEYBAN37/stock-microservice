package com.example.stock.article.articleapplicationtest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.application.articule.query.ArticleGetSale;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleSaleCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.service.ArticleSaleService;
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

class ArticleGetSaleTest {

    @InjectMocks
    private ArticleGetSale articleGetSale;

    @Mock
    private ArticleSaleService articleSaleService;

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_ValidCommands() {
        // Arrange
        List<ArticleSaleCommand> commands = Arrays.asList(
                new ArticleSaleCommand(1L, 2),
                new ArticleSaleCommand(2L, 1)
        );

        Article article1 =  new Article(1L,"Article2","dsfd",9,new BigDecimal("10.50"),new Brand(),Arrays.asList(new Category(), new Category()));
        Article article2 = new Article(2L,"Article2","dsfd",9,new BigDecimal("10.50"),new Brand(),Arrays.asList(new Category(), new Category()));
        List<Article> articles = Arrays.asList(article1, article2);

        List<ArticleDto> expectedDtos = Arrays.asList(
                new ArticleDto(1L,"Article2","dsfd",9,new BigDecimal("10.50"),new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle(), new CategoryDtoArticle()}),
                new ArticleDto(2L, "Article2","dsfd",9,new BigDecimal("10.50"),new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle(), new CategoryDtoArticle()})
        );

        when(articleSaleService.execute(commands)).thenReturn(articles);
        when(articleDtoMapper.toDto(article1)).thenReturn(expectedDtos.get(0));
        when(articleDtoMapper.toDto(article2)).thenReturn(expectedDtos.get(1));

        // Act
        List<ArticleDto> resultDtos = articleGetSale.execute(commands);

        // Assert
        assertEquals(expectedDtos, resultDtos);
        verify(articleSaleService).execute(commands);
        verify(articleDtoMapper, times(2)).toDto(any(Article.class));
    }

    @Test
    public void testExecute_NullArticles() {
        // Arrange
        List<ArticleSaleCommand> commands = Arrays.asList(new ArticleSaleCommand(1L, 2));
        when(articleSaleService.execute(commands)).thenReturn(Collections.emptyList());

        // Act
        List<ArticleDto> resultDtos = articleGetSale.execute(commands);

        // Assert
        assertTrue(resultDtos.isEmpty());
        verify(articleDtoMapper, never()).toDto(any());
    }
}
