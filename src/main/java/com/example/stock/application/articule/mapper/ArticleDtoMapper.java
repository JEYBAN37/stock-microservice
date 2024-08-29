package com.example.stock.application.articule.mapper;


import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "articleCategories", target = "articleCategories")
    ArticleDto toDto (Article objectOfDomain);
}
