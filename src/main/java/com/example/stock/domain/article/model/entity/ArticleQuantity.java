package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.exception.BrandException;
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
        if(String.valueOf(quantity).isEmpty())
            throw new BrandException("quantity is mandatory");
        if (quantity < 0) {
            throw new ArticleException("Quantity must be greater than zero");
        }
    }
}
