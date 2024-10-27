package com.example.stock.application.articule.query;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleSaleCommand;
import com.example.stock.domain.article.service.ArticleSaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ArticleGetSale {
    private final ArticleSaleService articleSaleService;
    private final ArticleDtoMapper articleDtoMapper;

    public List<ArticleDto> execute(List<ArticleSaleCommand> articleSaleCommands) {
        return articleSaleService.execute(articleSaleCommands).stream()
                .map(articleDtoMapper::toDto)
                .toList();
    }
}
