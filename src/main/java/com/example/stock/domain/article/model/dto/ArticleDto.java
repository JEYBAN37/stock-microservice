package com.example.stock.domain.article.model.dto;

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
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
}
