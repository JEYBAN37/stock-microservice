package com.example.stock.domain.article.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ArticleSaleCommand {
    private Long id;
    private int quantity;
}
