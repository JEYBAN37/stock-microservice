package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;

@NoArgsConstructor
@Getter
public class ArticleName {
    String name;

    private ArticleName(String name) {
        this.name = name;
    }

    public static ArticleName of(String name) {
        toValidName(name);
        return new ArticleName(name);
    }

    private static void toValidName(String name){
        if(name == null || name.isEmpty())
            throw new ArticleException(NAME_MANDATORY);
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new ArticleException(NAME_MAX_ERROR);
    }
}
