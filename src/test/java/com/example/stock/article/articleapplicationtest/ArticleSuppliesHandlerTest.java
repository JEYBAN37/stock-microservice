package com.example.stock.article.articleapplicationtest;

import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.service.ArticleSuppliesService;

import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class ArticleSuppliesHandlerTest {

    @Mock
    private ArticleSuppliesService articleSuppliesService;

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @InjectMocks
    private ArticleSuppliesHandler articleSuppliesHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testExecute_Success() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(new ArticleEditCommand(1L, 10, new BigDecimal(100.0)));
        Article article = new Article(1L, "Article1", "Description1", 5,  new BigDecimal(50.0), new Brand(), null);
        ArticleDto articleDto = new ArticleDto("Article1", "Description1", 15, new BigDecimal(100.0), new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});

        when(articleSuppliesService.execute(commands)).thenReturn(Arrays.asList(article));
        when(articleDtoMapper.toDto(article)).thenReturn(articleDto);

        // Act
        List<ArticleDto> result = articleSuppliesHandler.execute(commands);

        // Assert
        assertEquals(1, result.size());
        assertEquals(articleDto, result.get(0));
        verify(articleSuppliesService).execute(commands);
        verify(articleDtoMapper).toDto(article);
    }

    @Test
     void testExecute_NullPriceInCommands() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(new ArticleEditCommand(1L, 10, null));
        Article articleFound = new Article(1L, "Article1", "Description1", 5, new BigDecimal(50.0), new Brand(), null);
        ArticleDto articleDto = new ArticleDto("Article1", "Description1", 15, new BigDecimal(100.0), new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});

        when(articleSuppliesService.execute(commands)).thenReturn(Arrays.asList(articleFound));
        when(articleDtoMapper.toDto(articleFound)).thenReturn(articleDto);

        // Act
        List<ArticleDto> result = articleSuppliesHandler.execute(commands);

        // Assert
        assertEquals(1, result.size());
        assertEquals(articleDto, result.get(0));
        verify(articleSuppliesService).execute(commands);
        verify(articleDtoMapper).toDto(articleFound);
    }

    @Test
    public void testExecute_NonExistentArticle() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(new ArticleEditCommand(99L, 10, new BigDecimal(100.0)));

        when(articleSuppliesService.execute(commands)).thenThrow(new ArticleException("Article No Exist"));

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> articleSuppliesHandler.execute(commands));
        assertEquals("Article No Exist", exception.getErrorMessage());
        verify(articleSuppliesService).execute(commands);
    }

    @Test
    public void testExecute_MultipleArticlesSuccess() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(
                new ArticleEditCommand(1L, 10, new BigDecimal(100.0)),
                new ArticleEditCommand(2L, 5, new BigDecimal(50.0))
        );

        Article article1 = new Article(1L, "Article1", "Description1", 5, new BigDecimal(50.0), new Brand(), null);
        Article article2 = new Article(2L, "Article2", "Description2", 2, new BigDecimal(30.0), new Brand(), null);

        ArticleDto articleDto1 = new ArticleDto("Article1", "Description1", 15, new BigDecimal(100.0), new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});
        ArticleDto articleDto2 = new ArticleDto("Article1", "Description1", 7, new BigDecimal(100.0), new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});

        when(articleSuppliesService.execute(commands)).thenReturn(Arrays.asList(article1, article2));
        when(articleDtoMapper.toDto(article1)).thenReturn(articleDto1);
        when(articleDtoMapper.toDto(article2)).thenReturn(articleDto2);

        // Act
        List<ArticleDto> result = articleSuppliesHandler.execute(commands);

        // Assert
        assertEquals(2, result.size());
        assertEquals(articleDto1, result.get(0));
        assertEquals(articleDto2, result.get(1));
        verify(articleSuppliesService).execute(commands);
        verify(articleDtoMapper).toDto(article1);
        verify(articleDtoMapper).toDto(article2);
    }

    @Test
    public void testExecute_ArticleUpdateError() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(new ArticleEditCommand(3L, 10, new BigDecimal(100.0)));

        when(articleSuppliesService.execute(commands)).thenThrow(new ArticleException("Error updating article"));

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> articleSuppliesHandler.execute(commands));
        assertEquals("Error updating article", exception.getErrorMessage());
        verify(articleSuppliesService).execute(commands);
    }
}
