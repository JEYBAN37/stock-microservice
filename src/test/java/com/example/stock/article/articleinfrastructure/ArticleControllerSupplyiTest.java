package com.example.stock.article.articleinfrastructure;

import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;
import com.example.stock.infrastructure.article.rest.controller.ArticleCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

 class ArticleControllerSupplyiTest {
    @Mock
    private ArticleSuppliesHandler articleSuppliesHandler;

    @InjectMocks
    private ArticleCommandController articleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testUpdate_Success() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(new ArticleEditCommand(1L, 10, new BigDecimal(100.0)));
        ArticleDto articleDto = new ArticleDto( "Article1", "Description1",
                15, new BigDecimal(100.0),
                new BrandDtoArticle(),new CategoryDtoArticle[]{new CategoryDtoArticle()});

        when(articleSuppliesHandler.execute(commands)).thenReturn(Arrays.asList(articleDto));

        // Act
        List<ArticleDto> result = articleController.supplies(commands);

        // Assert
        assertEquals(1, result.size());
        assertEquals(articleDto, result.get(0));
    }


    @Test
     void testUpdate_NonExistentArticle() {
        // Arrange
        List<ArticleEditCommand> commands = Arrays.asList(new ArticleEditCommand(99L, 10, new BigDecimal(100.0)));

        when(articleSuppliesHandler.execute(commands)).thenThrow(new ArticleException("Article No Exist"));

        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> articleController.supplies(commands));
        assertEquals("Article No Exist", exception.getErrorMessage());
    }
}
