package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ArticleName {
    private static final int MAXIMUM_ALLOW_LETTERS = 50;
    String name;

    public ArticleName(String name) {
        toValidName(name);
        this.name = name;
    }

    private void toValidName(String name){
        if(name.isEmpty())
            throw new ArticleException("Name is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new ArticleException("Name don't be bigger than 50 characters");
    }
}
