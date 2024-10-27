package com.example.stock.application.articule.query;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.service.ArticleGetByIdsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ArticleGetById {
    private final ArticleGetByIdsService articleGetByIdsService;
    private final ArticleDtoMapper articleDtoMapper;
    public List<ArticleDto> execute (List<Long> ids, int page, int size, boolean ascending, String byName,
                                     String byBrand, String byCategory){
        return articleGetByIdsService.execute(ids,page, size, ascending, byName, byBrand, byCategory)
                .stream()
                .map(articleDtoMapper::toDto)
                .toList();
    }
}