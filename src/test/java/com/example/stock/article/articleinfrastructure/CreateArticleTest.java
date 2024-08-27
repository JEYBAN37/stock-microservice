package com.example.stock.article.articleinfrastructure;


import com.example.stock.application.articule.command.ArticleCreateHandler;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.infrastructure.article.rest.controller.ArticleCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateArticleTest {
    @Mock
    private ArticleCreateHandler articleCreateHandler;

    @InjectMocks
    private ArticleCommandController articleCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticle_successful() {
        // arrange
        ArticleDto mockArticleDto = new ArticleDto();
        mockArticleDto.setName("Test Article");
        mockArticleDto.setDescription("This is a test Article");
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setName("Test Article");
        mockCreateCommand.setDescription("This is a test Article");
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class))).thenReturn(mockArticleDto);
        // act
        ArticleDto result = articleCommandController.create(mockCreateCommand);
        // assert
        assertEquals(mockArticleDto.getName(), result.getName());
        assertEquals(mockArticleDto.getDescription(), result.getDescription());

    }
    @Test
    void createArticle_whenNameExist_shouldThrowsArticleException() {
        // arrange
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setName("Existing Article Name");
        mockCreateCommand.setDescription("This is a test Article");
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class)))
                .thenThrow(new ArticleException("Article with this name already exists"));
        // act & Assert
        assertThrows(ArticleException.class, () -> {
            articleCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createArticle_whenNameExceedsMaxLength_shouldThrowArticleException() {
        // arrange
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setName("This name is way too long and exceeds fifty characters");
        mockCreateCommand.setDescription("Valid description");
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class)))
                .thenThrow(new ArticleException("Name exceeds maximum length of 50 characters"));
        // act & Assert
        assertThrows(ArticleException.class, () -> {
            articleCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createArticle_whenDescriptionIsMissing_shouldThrowArticleException() {
        // arrange
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setName("Valid Name");
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class)))
                .thenThrow(new ArticleException("Description is required"));
        // act & Assert
        assertThrows(ArticleException.class, () -> {
            articleCommandController.create(mockCreateCommand);
        });
    }
    @Test
    void createArticle_whenDescriptionExceedsMaxLength_shouldThrowArticleException() {
        // arrange
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setName("Valid Name");
        mockCreateCommand.setDescription("This description is way too long and exceeds the ninety characters limit imposed by the system");
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class)))
                .thenThrow(new ArticleException("Description exceeds maximum length of 90 characters"));
        // act & Assert
        assertThrows(ArticleException.class, () -> {
            articleCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createArticle_whenBrandNoExist_shouldThrowArticleException() {
        // arrange
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setBrand(9L);
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class)))
                .thenThrow(new ArticleException("No found Brand with id 9"));
        // act & Assert
        assertThrows(ArticleException.class, () -> {
            articleCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createArticle_whenBrandNull_shouldThrowArticleException() {
        // arrange
        ArticleCreateCommand mockCreateCommand = new ArticleCreateCommand();
        mockCreateCommand.setBrand(null);
        when(articleCreateHandler.execute(any(ArticleCreateCommand.class)))
                .thenThrow(new ArticleException("Brand inject not found"));
        // act & Assert
        assertThrows(ArticleException.class, () -> {
            articleCommandController.create(mockCreateCommand);
        });
    }

}
