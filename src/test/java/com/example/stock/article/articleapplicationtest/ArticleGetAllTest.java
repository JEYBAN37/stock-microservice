package com.example.stock.article.articleapplicationtest;

import com.example.stock.application.articule.query.ArticleGetAll;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.service.ArticleFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ArticleGetAllTest {

    @Mock
    private ArticleFilterService articleFilterService;

    @InjectMocks
    private ArticleGetAll articleGetAll;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void articleGrtAll_whenExecuteWithAllParameters() {
        // arrange
        int page = 1;
        int size = 5;
        boolean ascending = true;
        String byName = "testName";
        String byBrand = "testBrand";
        String byCategory = "testCategory";

        List<ArticleDto> expectedArticles = List.of(new ArticleDto());

        when(articleFilterService.execute(anyInt(), anyInt(), anyBoolean(), anyString(), anyString(), anyString()))
                .thenReturn(expectedArticles);

        // act
        List<ArticleDto> result = articleGetAll.execute(page, size, ascending, byName, byBrand, byCategory);

        ArgumentCaptor<Integer> pageCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> sizeCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Boolean> ascendingCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> brandCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> categoryCaptor = ArgumentCaptor.forClass(String.class);

        // assert
        verify(articleFilterService).execute(pageCaptor.capture(), sizeCaptor.capture(),
                ascendingCaptor.capture(), nameCaptor.capture(), brandCaptor.capture(), categoryCaptor.capture());

        assertEquals(page, pageCaptor.getValue());
        assertEquals(size, sizeCaptor.getValue());
        assertEquals(ascending, ascendingCaptor.getValue());
        assertEquals(byName, nameCaptor.getValue());
        assertEquals(byBrand, brandCaptor.getValue());
        assertEquals(byCategory, categoryCaptor.getValue());

        assertEquals(expectedArticles, result);
    }


    @Test
     void articleGetAll_whenExecuteWithNullPageAndSize() {
        // arrange
        Boolean ascending = true;
        String byName = "testName";
        String byBrand = "testBrand";
        String byCategory = "testCategory";

        List<ArticleDto> expectedArticles = List.of(new ArticleDto());

        when(articleFilterService.execute(0, 10, ascending, byName, byBrand, byCategory))
                .thenReturn(expectedArticles);

        // act
        List<ArticleDto> result = articleGetAll.execute(null, null, ascending, byName, byBrand, byCategory);

        // assert
        assertEquals(expectedArticles, result);
        verify(articleFilterService, times(1)).execute(0, 10, ascending, byName, byBrand, byCategory);
    }

    @Test
     void articleGetAll_whenExecuteWithNullAscending() {
        // arrange
        int page = 1;
        int size = 5;
        String byName = "testName";
        String byBrand = "testBrand";
        String byCategory = "testCategory";

        List<ArticleDto> expectedArticles = List.of(new ArticleDto());

        when(articleFilterService.execute(page, size, false, byName, byBrand, byCategory))
                .thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleGetAll.execute(page, size, null, byName, byBrand, byCategory);
        // assert
        assertEquals(expectedArticles, result);
        verify(articleFilterService, times(1)).execute(page, size, false, byName, byBrand, byCategory);
    }

    @Test
     void articleGetAll_whenExecuteWithAllNullParameters() {
        // arrange
        List<ArticleDto> expectedArticles = List.of(new ArticleDto());

        when(articleFilterService.execute(0, 10, false, null, null, null))
                .thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleGetAll.execute(null, null, null, null, null, null);

        // assert
        assertEquals(expectedArticles, result);
        verify(articleFilterService, times(1)).execute(0, 10, false, null, null, null);
    }

    @Test
     void articleGetAll_whenExecuteWithSomeNullParameters() {
        // arrange
        int page = 2;
        String byName = "testName";
        Boolean ascending = true;

        List<ArticleDto> expectedArticles = List.of(new ArticleDto());

        when(articleFilterService.execute(page, 10, ascending, byName, null, null))
                .thenReturn(expectedArticles);
        // act
        List<ArticleDto> result = articleGetAll.execute(page, null, ascending, byName, null, null);

        // assert
        assertEquals(expectedArticles, result);
        verify(articleFilterService, times(1)).execute(page, 10, ascending, byName, null, null);
    }
}
