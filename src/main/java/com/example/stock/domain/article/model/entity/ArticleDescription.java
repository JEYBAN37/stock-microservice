package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ArticleDescription {
    private static final int MAXIMUM_ALLOW_LETTERS = 90;
    String description;

    public ArticleDescription(String description){
        toValidDescription(description);
        this.description = description;
    }
    private void toValidDescription(String name){
        if(name.isEmpty())
            throw new ArticleException("Description is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new ArticleException("Description don't be bigger than 120 characters");
    }
}
