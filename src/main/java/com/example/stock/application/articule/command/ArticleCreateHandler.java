package com.example.stock.application.articule.command;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.article.service.ArticleCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleCreateHandler {
    private final ArticleCreateService articleCreateService;
    private final ArticleDtoMapper articleDtoMapper;

    @Autowired
    public ArticleCreateHandler(ArticleCreateService articleCreateService, ArticleDtoMapper articleDtoMapper) {
        this.articleCreateService = articleCreateService;
        this.articleDtoMapper = articleDtoMapper;
    }

    public ArticleDto execute (ArticleCreateCommand createCommand){
        return articleDtoMapper.toDto(articleCreateService.execute(createCommand));
    }
}
