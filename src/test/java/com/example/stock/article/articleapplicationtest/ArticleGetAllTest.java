package com.example.stock.article.articleapplicationtest;
import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.application.articule.query.ArticleGetAll;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.service.ArticleFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleGetAllTest {

    @Mock
    private ArticleFilterService articleFilterService;

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @InjectMocks
    private ArticleGetAll articleGetAll;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteWithValidParameters() {
        Integer page = 1;
        Integer size = 5;
        Boolean ascending = true;
        String byName = "ExampleName";
        String byBrand = "ExampleBrand";
        String byCategory = "ExampleCategory";

        List<Article> articles = Collections.singletonList(new Article());
        List<ArticleDto> articleDtos = Collections.singletonList(new ArticleDto());

        when(articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory)).thenReturn(articles);
        when(articleDtoMapper.toDto(any(Article.class))).thenReturn(articleDtos.get(0));

        List<ArticleDto> result = articleGetAll.execute(page, size, ascending, byName, byBrand, byCategory);

        assertEquals(articleDtos, result);
        verify(articleFilterService, times(1)).execute(page, size, ascending, byName, byBrand, byCategory);
        verify(articleDtoMapper, times(articles.size())).toDto(any(Article.class));
    }

    @Test
    void testExecuteWithNullParameters() {
        // Parámetros nulos
        Integer page = null;
        Integer size = null;
        Boolean ascending = null;

        String byName = "ExampleName";
        String byBrand = "ExampleBrand";
        String byCategory = "ExampleCategory";

        List<Article> articles = Collections.singletonList(new Article());
        List<ArticleDto> articleDtos = Collections.singletonList(new ArticleDto());

        when(articleFilterService.execute(0, 10, false, byName, byBrand, byCategory)).thenReturn(articles);
        when(articleDtoMapper.toDto(any(Article.class))).thenReturn(articleDtos.get(0));

        List<ArticleDto> result = articleGetAll.execute(page, size, ascending, byName, byBrand, byCategory);

        assertEquals(articleDtos, result);
        verify(articleFilterService, times(1)).execute(0, 10, false, byName, byBrand, byCategory);
        verify(articleDtoMapper, times(articles.size())).toDto(any(Article.class));
    }
}
