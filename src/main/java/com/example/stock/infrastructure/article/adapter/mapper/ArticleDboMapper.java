package com.example.stock.infrastructure.article.adapter.mapper;

import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class ArticleDboMapper {

public ArticleEntity toDatabase (Article domain){
    if(domain == null){
        return null;
    }
        CategoryEntity[] categoryEntities = Arrays.stream(domain.getArticleCategories())
            .map(category -> new CategoryEntity(category.getId(), category.getName(), category.getDescription()))
            .toArray(CategoryEntity[]::new);

    Brand brandOfDomain = domain.getBrand();
    BrandEntity brandEntity = new BrandEntity(brandOfDomain.getId(),brandOfDomain.getName(),brandOfDomain.getDescription());

    return new ArticleEntity(
            domain.getId(),
            domain.getName(),
            domain.getDescription(),
            domain.getQuantity(),
            domain.getPrice(),
            brandEntity,
            categoryEntities
    );
}

    public Article toDomain(ArticleEntity entity){
        if(entity == null){
            return null;
        }
        Category[] entitiesToCategories = Arrays.stream(entity.getArticleCategories())
                .map(category -> new Category(category.getId(), category.getName(), category.getDescription()))
                .toArray(Category[]::new);

        BrandEntity brandEntity = entity.getBrand();
        Brand brandEntityToBrand = new Brand(brandEntity.getId(), brandEntity.getName(), brandEntity.getDescription());

        return new Article(entity.getId(), entity.getName(), entity.getDescription(),entity.getQuantity(),entity.getPrice(),brandEntityToBrand,entitiesToCategories);
    }

}
