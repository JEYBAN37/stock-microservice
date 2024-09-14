package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;


@NoArgsConstructor
@Getter
public class ArticleDescription {

    String description;

    private ArticleDescription(String description){
        this.description = description;
    }
    public static ArticleDescription of(String description) {
        toValidDescription(description);
        return new ArticleDescription(description);
    }

    private static void toValidDescription(String name){
        if(name == null || name.isEmpty())
            throw new ArticleException(DESCRIPTION_MESSAGE);
        if(name.length() > MAXIMUM_ALLOW_LETTERS_VAR)
            throw new ArticleException(DESCRIPTION_MESSAGE_MAX_ERROR_VAR);
    }
}
