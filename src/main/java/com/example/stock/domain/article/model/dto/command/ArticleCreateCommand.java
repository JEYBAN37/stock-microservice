package com.example.stock.domain.article.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticleCreateCommand {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private Long brand;
    private List<Long> articleCategories;
}
