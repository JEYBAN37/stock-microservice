package com.example.stock.domain.article.model.dto.command;

import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticleEditCommand {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private Brand brand;
    private Category[] articleCategories;
}
