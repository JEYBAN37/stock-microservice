package com.example.stock.article.articleapplicationtest;

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
        List<ArticleDto> result = articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory);

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
     void filterService_differentParameters() {
       Article article1 = new Article();
       Article article2 = new Article();
       ArticleDto articleDto1 = new ArticleDto();
       ArticleDto articleDto2 = new ArticleDto();

       when(articleDao.getAll(1, 5, false, "name1", "brand1", "category1"))
               .thenReturn(List.of(article1, article2));
       when(articleDtoMapper.toDto(article1)).thenReturn(articleDto1);
       when(articleDtoMapper.toDto(article2)).thenReturn(articleDto2);

       List<ArticleDto> result = articleFilterService.execute(1, 5, false, "name1", "brand1", "category1");

       assertEquals(2, result.size());
       assertEquals(articleDto1, result.get(0));
       assertEquals(articleDto2, result.get(1));

       verify(articleDao, times(1)).getAll(1, 5, false, "name1", "brand1", "category1");
       verify(articleDtoMapper, times(1)).toDto(article1);
       verify(articleDtoMapper, times(1)).toDto(article2);
    }

    @Test
     void filterService_withLargePageAndSize() {
       // arrange
       int page = 100;
       int size = 1000;
       boolean ascending = true;
       String byName = "test";
       String byBrand = "brand";
       String byCategory = "category";

       Article article = new Article();
       ArticleDto articleDto = new ArticleDto();

       when(articleDao.getAll(page, size, ascending, byName, byBrand, byCategory))
               .thenReturn(List.of(article));
       when(articleDtoMapper.toDto(article))
               .thenReturn(articleDto);

       // act
       List<ArticleDto> result = articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory);

       // assert
       assertEquals(1, result.size());
       assertEquals(articleDto, result.get(0));

       verify(articleDao, times(1)).getAll(page, size, ascending, byName, byBrand, byCategory);
       verify(articleDtoMapper, times(1)).toDto(article);
    }


    @Test
    void testExecuteThrowsExceptionWhenDtoMapperFails() {
       // arrange
       int page = 0;
       int size = 10;
       boolean ascending = true;
       String byName = "test";
       String byBrand = "brand";
       String byCategory = "category";

       Article article = new Article();

       when(articleDao.getAll(page, size, ascending, byName, byBrand, byCategory))
               .thenReturn(List.of(article));

       when(articleDtoMapper.toDto(article))
               .thenThrow(new RuntimeException("Mapping error"));
       // act y assert
       RuntimeException exception = assertThrows(RuntimeException.class, () ->
               articleFilterService.execute(page, size, ascending, byName, byBrand, byCategory)
       );
       assertEquals("Mapping error", exception.getMessage());
       verify(articleDao, times(1)).getAll(page, size, ascending, byName, byBrand, byCategory);
       verify(articleDtoMapper, times(1)).toDto(article); // El mapeador debería haber sido llamado
    }
}