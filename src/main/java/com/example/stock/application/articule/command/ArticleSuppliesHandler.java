package com.example.stock.application.articule.command;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;

import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.service.ArticleSuppliesService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArticleSuppliesHandler {
    private final ArticleSuppliesService articleSuppliesService;
    private final ArticleDtoMapper articleDtoMapper;

    public List<ArticleDto> execute(List<ArticleEditCommand> articleEditCommands) {
        return articleSuppliesService.execute(articleEditCommands).stream()
                .map(articleDtoMapper::toDto)
                .toList();
    }

}
