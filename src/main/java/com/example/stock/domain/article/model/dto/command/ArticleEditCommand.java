package com.example.stock.domain.article.model.dto.command;
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
    private int quantity;
    private BigDecimal price;
}
