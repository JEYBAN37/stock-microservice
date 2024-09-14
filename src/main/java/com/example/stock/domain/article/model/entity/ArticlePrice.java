package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.example.stock.domain.static_variables.StaticData.*;

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
            throw new BrandException(PRICE_MANDATORY);
        if (price.compareTo(BigDecimal.ZERO) <= ZERO_CONSTANT) {
            throw new ArticleException(PRICE_MIN_ZERO);
        }
    }

}
