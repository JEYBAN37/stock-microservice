package com.example.stock.article.articledomaintest;
import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.application.articule.query.ArticleByName;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.brand.model.entity.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ArticleByNameTest {

    @Mock
    private ArticleDao articleDao;

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @InjectMocks
    private ArticleByName articleByName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ValidName_ReturnsArticleDto() {
        // Arrange
        String articleName = "SampleArticle";
        Article article = new Article(1L, articleName, "Sample Description", 10, new BigDecimal(100.0), new Brand(), null);
        ArticleDto articleDto = new ArticleDto( articleName, "Sample Description", 10, new BigDecimal(100.0), new BrandDtoArticle(), null);

        when(articleDao.getByName(articleName)).thenReturn(article);
        when(articleDtoMapper.toDto(article)).thenReturn(articleDto);

        // Act
        ArticleDto result = articleByName.execute(articleName);

        // Assert
        assertEquals(articleDto, result);
        verify(articleDao, times(1)).getByName(articleName);
        verify(articleDtoMapper, times(1)).toDto(article);
    }

}
