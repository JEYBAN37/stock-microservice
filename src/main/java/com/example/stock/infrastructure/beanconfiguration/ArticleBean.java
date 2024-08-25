package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.article.service.ArticleCreateService;
import com.example.stock.domain.article.service.ArticleDeleteService;
import com.example.stock.domain.article.service.ArticleUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ArticleBean{
    @Bean
    public ArticleCreateService articleCreateService (ArticleRepository articleRepository, ArticleDao articleDao){
        return new ArticleCreateService(articleRepository,articleDao);
    }
    @Bean
    public ArticleDeleteService articleDeleteService (ArticleRepository articleRepository, ArticleDao articleDao){
        return new ArticleDeleteService(articleRepository,articleDao);
    }
    @Bean
    public ArticleUpdateService articleUpdateService(ArticleRepository articleRepository, ArticleDao articleDao){
        return new ArticleUpdateService(articleRepository, articleDao);
    }

}
