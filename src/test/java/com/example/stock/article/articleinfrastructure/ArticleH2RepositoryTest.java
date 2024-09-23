package com.example.stock.article.articleinfrastructure;


import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import com.example.stock.infrastructure.article.adapter.jpa.ArticleSpringJpaAdapterRepository;
import com.example.stock.infrastructure.article.adapter.jpa.respository.ArticleH2Repository;
import com.example.stock.infrastructure.article.adapter.mapper.ArticleDboMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

 class ArticleH2RepositoryTest {

    private ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;
    private ArticleDboMapper articleDboMapper;
    private ArticleH2Repository articleH2Repository;

    @BeforeEach
    public void setUp() {
        articleSpringJpaAdapterRepository = mock(ArticleSpringJpaAdapterRepository.class);
        articleDboMapper = mock(ArticleDboMapper.class);
        articleH2Repository = new ArticleH2Repository(articleSpringJpaAdapterRepository, articleDboMapper);
    }

    @Test
     void create_ShouldSaveAndReturnArticle() {
        // Arrange
        Article articleRequest = new Article(/* initialize fields */);
        ArticleEntity articleEntity = new ArticleEntity(/* initialize fields */);
        ArticleEntity savedArticleEntity = new ArticleEntity(/* initialize fields */);

        when(articleDboMapper.toDatabase(articleRequest)).thenReturn(articleEntity);
        when(articleSpringJpaAdapterRepository.save(articleEntity)).thenReturn(savedArticleEntity);
        when(articleDboMapper.toDomain(savedArticleEntity)).thenReturn(articleRequest);

        // Act
        Article result = articleH2Repository.create(articleRequest);

        // Assert
        assertThat(result).isEqualTo(articleRequest);
        verify(articleSpringJpaAdapterRepository, times(1)).save(articleEntity);
    }

    @Test
     void deleteById_ShouldDeleteArticleById() {
        // Arrange
        Long articleId = 1L;

        // Act
        articleH2Repository.deleteById(articleId);

        // Assert
        verify(articleSpringJpaAdapterRepository, times(1)).deleteById(articleId);
    }

    @Test
     void update_ShouldUpdateAndReturnArticle() {
        // Arrange
        Article articleRequest = new Article(/* initialize fields */);
        ArticleEntity articleEntity = new ArticleEntity(/* initialize fields */);
        ArticleEntity updatedArticleEntity = new ArticleEntity(/* initialize fields */);

        when(articleDboMapper.toDatabase(articleRequest)).thenReturn(articleEntity);
        when(articleSpringJpaAdapterRepository.save(articleEntity)).thenReturn(updatedArticleEntity);
        when(articleDboMapper.toDomain(updatedArticleEntity)).thenReturn(articleRequest);

        // Act
        Article result = articleH2Repository.update(articleRequest);

        // Assert
        assertThat(result).isEqualTo(articleRequest);
        verify(articleSpringJpaAdapterRepository, times(1)).save(articleEntity);
    }
}
