package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class ArticlePrice {
    BigDecimal price;
    private ArticlePrice (BigDecimal price){
        this.price = price;
    }

    public static ArticlePrice of(BigDecimal price) {
        toValidPrice(price);
        return new ArticlePrice(price);
    }

    private static void toValidPrice(BigDecimal price) {
        if(price == null)
            throw new BrandException("price is mandatory");
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArticleException("Price must be greater than zero.");
        }
    }

}
