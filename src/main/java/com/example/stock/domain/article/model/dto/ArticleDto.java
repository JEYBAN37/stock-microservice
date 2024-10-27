package com.example.stock.domain.article.model.dto;

import com.example.stock.domain.brand.model.dto.BrandDtoArticle;
import com.example.stock.domain.category.model.dto.CategoryDtoArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private BrandDtoArticle brand;
    private CategoryDtoArticle[] articleCategories;

}
