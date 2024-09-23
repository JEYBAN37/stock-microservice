package com.example.stock.article.articleinfrastructure;

import com.example.stock.application.articule.query.ArticleByName;
import com.example.stock.application.articule.query.ArticleGetAll;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.infrastructure.article.rest.controller.ArticleQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleQueryControllerTest {

    @Mock
    private ArticleByName articleByName;

    @Mock
    private ArticleGetAll articleGetAll;

    @InjectMocks
    private ArticleQueryController articleQueryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void getByName_ShouldReturnArticleDto() {
        // Arrange
        String name = "Sample Article";
        ArticleDto expectedArticleDto = new ArticleDto();
        when(articleByName.execute(name)).thenReturn(expectedArticleDto);

        // Act
        ArticleDto actualArticleDto = articleQueryController.getByName(name);

        // Assert
        assertEquals(expectedArticleDto, actualArticleDto);
        verify(articleByName, times(1)).execute(name);
    }

    @Test
    public void getAll_ShouldReturnListOfArticleDto() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;
        List<ArticleDto> expectedArticles = Arrays.asList(new ArticleDto(), new ArticleDto());
        when(articleGetAll.execute(page, size, ascending, null, null, null)).thenReturn(expectedArticles);

        // Act
        List<ArticleDto> actualArticles = articleQueryController.getAll(page, size, ascending, null, null, null);

        // Assert
        assertEquals(expectedArticles, actualArticles);
        verify(articleGetAll, times(1)).execute(page, size, ascending, null, null, null);
    }

    @Test
    public void getAll_ShouldReturnEmptyListWhenNoArticles() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;
        when(articleGetAll.execute(page, size, ascending, null, null, null)).thenReturn(Collections.emptyList());

        // Act
        List<ArticleDto> actualArticles = articleQueryController.getAll(page, size, ascending, null, null, null);

        // Assert
        assertEquals(Collections.emptyList(), actualArticles);
        verify(articleGetAll, times(1)).execute(page, size, ascending, null, null, null);
    }
}
