package com.example.stock.article.articleinfrastructure;



import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import com.example.stock.infrastructure.article.adapter.jpa.ArticleSpringJpaAdapterRepository;
import com.example.stock.infrastructure.article.adapter.jpa.dao.ArticleH2Dao;
import com.example.stock.infrastructure.article.adapter.mapper.ArticleDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ArticleH2DaoTest {

    @Mock
    private ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;

    @Mock
    private ArticleDboMapper articleDboMapper;

    @InjectMocks
    private ArticleH2Dao articleH2Dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByName_ShouldReturnArticle_WhenArticleExists() {
        // Arrange
        String articleName = "Article A";
        ArticleEntity articleEntity = new ArticleEntity(); // Inicializa con datos de prueba
        Article expectedArticle = new Article(); // Inicializa con datos de prueba

        when(articleSpringJpaAdapterRepository.findByName(articleName)).thenReturn(articleEntity);
        when(articleDboMapper.toDomain(articleEntity)).thenReturn(expectedArticle);

        // Act
        Article actualArticle = articleH2Dao.getByName(articleName);

        // Assert
        assertThat(actualArticle).isEqualTo(expectedArticle);
    }

    @Test
     void getById_ShouldReturnArticle_WhenArticleExists() {
        // Arrange
        Long articleId = 1L;
        ArticleEntity articleEntity = new ArticleEntity();
        Article expectedArticle = new Article();

        when(articleSpringJpaAdapterRepository.findByIdWithCategories(articleId)).thenReturn(Optional.of(articleEntity));
        when(articleDboMapper.toDomain(articleEntity)).thenReturn(expectedArticle);

        // Act
        Article actualArticle = articleH2Dao.getById(articleId);

        // Assert
        assertThat(actualArticle).isEqualTo(expectedArticle);
    }

    @Test
     void getById_ShouldReturnNull_WhenArticleDoesNotExist() {
        // Arrange
        Long articleId = 1L;

        when(articleSpringJpaAdapterRepository.findByIdWithCategories(articleId)).thenReturn(Optional.empty());

        // Act
        Article actualArticle = articleH2Dao.getById(articleId);

        // Assert
        assertThat(actualArticle).isNull();
    }

    @Test
     void nameExist_ShouldReturnTrue_WhenNameExists() {
        // Arrange
        String articleName = "Article A";

        when(articleSpringJpaAdapterRepository.existsByName(articleName)).thenReturn(true);

        // Act
        boolean result = articleH2Dao.nameExist(articleName);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
     void nameExist_ShouldReturnFalse_WhenNameDoesNotExist() {
        // Arrange
        String articleName = "Article Unknown";

        when(articleSpringJpaAdapterRepository.existsByName(articleName)).thenReturn(false);

        // Act
        boolean result = articleH2Dao.nameExist(articleName);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
     void idExist_ShouldReturnTrue_WhenIdExists() {
        // Arrange
        Long articleId = 1L;

        when(articleSpringJpaAdapterRepository.existsById(articleId)).thenReturn(true);

        // Act
        boolean result = articleH2Dao.idExist(articleId);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
     void idExist_ShouldReturnFalse_WhenIdDoesNotExist() {
        // Arrange
        Long articleId = 1L;

        when(articleSpringJpaAdapterRepository.existsById(articleId)).thenReturn(false);

        // Act
        boolean result = articleH2Dao.idExist(articleId);

        // Assert
        assertThat(result).isFalse();
    }
}
