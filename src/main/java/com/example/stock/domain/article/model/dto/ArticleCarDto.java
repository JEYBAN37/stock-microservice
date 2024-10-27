package com.example.stock.domain.article.model.dto;

import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleCarDto {
    private Long id;
    private int quantity;
    private BrandDtoArticle brand;
    private List<CategoryDtoArticle> articleCategories;
}
