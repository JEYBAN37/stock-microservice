package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleCreateService;
import com.example.stock.domain.article.service.ArticleFilterService;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.category.service.CategoryListArticle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleBean{
    @Bean
    public ArticleCreateService articleCreateService (ArticleRepository articleRepository, ArticleDao articleDao, CategoryListArticle categoryListArticle,
                                                      BrandDao brandDao
    ){
        return new ArticleCreateService(articleRepository,articleDao,categoryListArticle,brandDao);
    }
   @Bean
    public ArticleFilterService articleFilterService (ArticleDtoMapper articleDtoMapper,ArticleDao articleDao){
        return new ArticleFilterService(articleDtoMapper,articleDao);
    }

}
