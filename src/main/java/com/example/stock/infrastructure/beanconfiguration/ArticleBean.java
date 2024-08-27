package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleCreateService;
import com.example.stock.domain.article.service.ArticleDeleteService;
import com.example.stock.domain.article.service.ArticleUpdateService;
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
    public ArticleDeleteService articleDeleteService (ArticleRepository articleRepository, ArticleDao articleDao){
        return new ArticleDeleteService(articleRepository,articleDao);
    }
    @Bean
    public ArticleUpdateService articleUpdateService(ArticleRepository articleRepository, ArticleDao articleDao, CategoryListArticle categoryListArticle,
                                                     BrandDao brandDao){
        return new ArticleUpdateService(articleRepository,articleDao,categoryListArticle,brandDao);
    }

}
