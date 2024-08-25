package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleQuantity {
    int quantity;
    public ArticleQuantity(int quantity) {
        toValidQuantity(quantity);
        this.quantity = quantity;
    }
    private void toValidQuantity(int quantity) {
        if (quantity < 0) {
            throw new ArticleException("Quantity must be greater than zero");
        }
    }
}
