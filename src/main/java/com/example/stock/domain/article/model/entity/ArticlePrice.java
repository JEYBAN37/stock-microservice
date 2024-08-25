package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class ArticlePrice {
    BigDecimal price;
    public ArticlePrice (BigDecimal price){
        toValidPrice(price);
        this.price = price;
    }
    private void toValidPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArticleException("Price must be greater than zero.");
        }
    }

}
