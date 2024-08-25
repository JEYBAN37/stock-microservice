package com.example.stock.infrastructure.article.adapter.mapper;

import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleDboMapper {

public ArticleEntity toDatabase (Article domain){
    if(domain == null){
        return null;
    }
    return new ArticleEntity(
            domain.getId(),
            domain.getName(),
            domain.getDescription(),
            domain.getQuantity(),
            domain.getPrice()
    );
}

    public Article toDomain(ArticleEntity entity){
        if(entity == null){
            return null;
        }

        return new Article(entity.getId(), entity.getName(), entity.getDescription(),entity.getQuantity(),entity.getPrice());
    }



}
