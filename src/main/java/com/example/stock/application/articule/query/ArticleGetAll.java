package com.example.stock.application.articule.query;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.service.ArticleFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ArticleGetAll {
    private final ArticleFilterService articleFilterService;
    private final ArticleDtoMapper articleDtoMapper;
    public List<ArticleDto> execute (Integer page,
                                     Integer size,
                                     Boolean ascending,
                                     String byName,
                                     String byBrand,
                                     String byCategory)
    {

        int pageNumber = (page == null) ? 0 : page;
        int pageSize = (size == null) ? 10 : size;
        boolean isAscending = (ascending != null) && ascending;

        return articleFilterService.execute(
                pageNumber,
                pageSize,
                isAscending,
                byName,
                byBrand,
                byCategory)
                .stream()
                .map(articleDtoMapper::toDto)
                .toList();
    }

}
