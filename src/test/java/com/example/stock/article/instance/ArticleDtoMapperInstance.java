package com.example.stock.article.instance;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;

public class ArticleDtoMapperInstance implements ArticleDtoMapper {
    @Override
    public ArticleDto toDto(Article domain) {
        if (domain == null) {
            return null;
        }

        ArticleDto dto = new ArticleDto();
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());

        return dto;
    }
}
