package com.example.stock.article.articleinfrastructure;

import com.example.stock.application.articule.query.ArticleGetAll;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.infrastructure.article.rest.controller.ArticleQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class filterControllerTest {
    @Mock
    private ArticleGetAll articleGetAll;

    @InjectMocks
    private ArticleQueryController articleQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnArticles() {
        BigDecimal price1 = new BigDecimal("19.99");
        // arrange
        ArticleDto articleDto1 = new ArticleDto(1L,"Article A", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        ArticleDto articleDto2 = new ArticleDto(2l,"Article B", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        List<ArticleDto> expectedArticles = List.of(articleDto1, articleDto2);
        when(articleGetAll.execute(0, 10, true, "Article", "Brand", "Category")).thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleQueryController.getAll(0, 10, true, "Article", "Brand", "Category");
        // assert
        assertEquals(expectedArticles, result);
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNoArticles() {
        // arrange
        when(articleGetAll.execute(0, 10, true, "Article", "Brand", "Category")).thenReturn(List.of());
        // act
        List<ArticleDto> result = articleQueryController.getAll(0, 10, true, "Article", "Brand", "Category");
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_shouldReturnArticlesInAscendingOrder() {
        BigDecimal price1 = new BigDecimal("19.99");
        // arrange
        ArticleDto articleDto1 =  new ArticleDto(1L,"Article A", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        ArticleDto articleDto2 = new ArticleDto(2L,"Article B", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        List<ArticleDto> expectedArticles = List.of(articleDto1, articleDto2);
        when(articleGetAll.execute(0, 10, true, null, null, null)).thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleQueryController.getAll(0, 10, true, null, null, null);
        // assert
        assertEquals(expectedArticles, result);
    }

    @Test
    void getAll_shouldReturnArticlesInDescendingOrder() {
        BigDecimal price1 = new BigDecimal("19.99");
        // arrange
        ArticleDto articleDto1 =  new ArticleDto(1L,"Article A", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        ArticleDto articleDto2 = new ArticleDto(2L,"Article B", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        List<ArticleDto> expectedArticles = List.of(articleDto1, articleDto2);
        when(articleGetAll.execute(0, 10, false, null, null, null)).thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleQueryController.getAll(0, 10, false, null, null, null);
        // assert
        assertEquals(expectedArticles, result);
    }

    @Test
    void getAll_shouldFilterByNameBrandAndCategory() {
        BigDecimal price1 = new BigDecimal("19.99");
        // arrange
        ArticleDto articleDto1 =  new ArticleDto(1L,"Article A", "Description",5,price1,new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});

        List<ArticleDto> expectedArticles = List.of(articleDto1);
        when(articleGetAll.execute(0, 10, true, "Article A", "Brand A", "Category A")).thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleQueryController.getAll(0, 10, true, "Article A", "Brand A", "Category A");
        // assert
        assertEquals(expectedArticles, result);
    }

    @Test
    void getAll_shouldHandleNoDataFound() {
        // arrange
        when(articleGetAll.execute(0, 10, true, null, null, null)).thenReturn(List.of());
        // act
        List<ArticleDto> result = articleQueryController.getAll(0, 10, true, null, null, null);
        // assert
        assertTrue(result.isEmpty());
    }
}
