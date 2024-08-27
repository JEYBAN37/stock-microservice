package com.example.stock.application.articule.command;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.service.ArticleUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleUpdateHandler {
    private final ArticleUpdateService articleUpdateService;
    private final ArticleDtoMapper articleDtoMapper;

    public ArticleDto execute (ArticleEditCommand articleEditCommand, Long id){
        return articleDtoMapper.toDto(articleUpdateService.execute(id,articleEditCommand));
    }
}

