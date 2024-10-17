package com.example.stock.article.instance;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleCarDto;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;

import java.util.List;

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

    @Override
    public ArticleCarDto cartoDto(Article objectOfDomain) {
        if (objectOfDomain == null) {
            return null;
        }

        ArticleCarDto dto = new ArticleCarDto();
        dto.setId(objectOfDomain.getId());
        dto.setQuantity(objectOfDomain.getQuantity());
        List<CategoryDtoArticle> categoryDtoArticles = objectOfDomain
                .getArticleCategories()
                .stream()
                .map( article -> new CategoryDtoArticle(article.getId(), article.getName())).toList();
        dto.setArticleCategories(categoryDtoArticles);
        return dto;
    }


}
