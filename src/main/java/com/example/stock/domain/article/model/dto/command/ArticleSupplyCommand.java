package com.example.stock.domain.article.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSupplyCommand {
    private Long id;
    private int quantity;
    private BigDecimal price;
}
