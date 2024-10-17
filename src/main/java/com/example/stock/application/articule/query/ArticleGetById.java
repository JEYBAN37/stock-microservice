package com.example.stock.application.articule.query;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleCarDto;
import com.example.stock.domain.article.service.ArticleGetByIdsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ArticleGetById {
    private final ArticleGetByIdsService articleGetByIdsService;
    private final ArticleDtoMapper articleDtoMapper;
    public List<ArticleCarDto> execute (List<Long> ids){
        return articleGetByIdsService.execute(ids)
                .stream()
                .map(articleDtoMapper::cartoDto)
                .toList();
    }
}