package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.MAXIMUM_ALLOW_LETTERS;
import static com.example.stock.domain.static_variables.StaticData.NAME_MANDATORY;
import static com.example.stock.domain.static_variables.StaticData.NAME_MAX_ERROR;

@NoArgsConstructor
@Getter
public class ArticleName {
    String name;

    private ArticleName(String name) {
        this.name = name;
    }

    public static ArticleName of(String name) {
        return new ArticleName(toValidName(name));
    }

    private static String toValidName(String name){
        if(name == null || name.isEmpty())
            throw new ArticleException(NAME_MANDATORY);
        String nameTrip = name.trim().toUpperCase();
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new ArticleException(NAME_MAX_ERROR);
        return nameTrip;
    }
}
