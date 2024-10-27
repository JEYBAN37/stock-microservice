package com.example.stock.article.articledomaintest;

import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.service.ArticleFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class ArticleFilterTest {

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @Mock
    private ArticleDao articleDao;

    @InjectMocks
    private ArticleFilterService articleFilterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void filterService_ValidParameters() {
        // arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;
        String byName = "name";
        String byBrand = "brand";
        String byCategory = "category";

        List<ArticleDto> articleDtos = Collections.singletonList(new ArticleDto());
        when(articleDao.getAll(page, size, ascending, byName, byBrand, byCategory)).thenReturn(Collections.emptyList());
        when(articleDtoMapper.toDto(any())).thenReturn(articleDtos.get(0));

        // act
        List<Article> result = articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory);

        // assert
        assertNotNull(result);
        verify(articleDao, times(1)).getAll(page, size, ascending, byName, byBrand, byCategory);
        verify(articleDtoMapper, times(0)).toDto(any());
    }

    @Test
     void filterService_whenInvalidPage_shouldThrowException() {

        // arrange
        int page = -1;
        int size = 10;

        // act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleFilterService.execute(page, size, true, "name", "brand", "category");
        });
        assertEquals("Page index must be non-negative and size must be greater than zero.", exception.getErrorMessage());
    }

    @Test
     void filterService_whenInvalidSize_shouldThrowException() {
        // arrange
        int page = 0;
        int size = 0;
        // act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleFilterService.execute(page, size, true, "name", "brand", "category");
        });
        assertEquals("Page index must be non-negative and size must be greater than zero.", exception.getErrorMessage());
    }


     @Test
     void testExecuteWithValidParameters() {
         int page = 0;
         int size = 10;
         boolean ascending = true;
         String byName = "ExampleName";
         String byBrand = "ExampleBrand";
         String byCategory = "ExampleCategory";

         List<Article> mockArticles = Collections.singletonList(new Article());
         when(articleDao.getAll(page, size, ascending, byName, byBrand, byCategory)).thenReturn(mockArticles);

         List<Article> result = articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory);

         assertEquals(mockArticles, result);
         verify(articleDao, times(1)).getAll(page, size, ascending, byName, byBrand, byCategory);
     }

     @Test
     void testExecuteWithNegativePage() {
         int page = -1;
         int size = 10;
         boolean ascending = true;

         assertThrows(ArticleException.class, () -> articleFilterService.execute(page, size, ascending, null, null, null));
     }

     @Test
     void testExecuteWithInvalidSize() {
         int page = 0;
         int size = 0;
         boolean ascending = true;

         assertThrows(ArticleException.class, () -> articleFilterService.execute(page, size, ascending, null, null, null));
     }
}