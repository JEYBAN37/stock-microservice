package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;

@Getter
@NoArgsConstructor
public class ArticleQuantity {
    int quantity;
    private ArticleQuantity(int quantity) {
        toValidQuantity(quantity);
        this.quantity = quantity;
    }
    public static ArticleQuantity of (int quantity){
        toValidQuantity(quantity);
        return new ArticleQuantity(quantity);
    }
    private static void toValidQuantity(int quantity) {
        if(String.valueOf(quantity) == null || String.valueOf(quantity).isEmpty())
            throw new ArticleException(QUANTITY_MANDATORY);
        if (quantity < ZERO_CONSTANT) {
            throw new ArticleException(QUANTITY_MESSAGE_MIN_ERROR);
        }
    }
}
