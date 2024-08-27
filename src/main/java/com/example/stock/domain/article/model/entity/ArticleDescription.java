package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.exception.ArticleException;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ArticleDescription {
    private static final int MAXIMUM_ALLOW_LETTERS = 90;
    String description;

    private ArticleDescription(String description){
        this.description = description;
    }
    public static ArticleDescription of(String description) {
        toValidDescription(description);
        return new ArticleDescription(description);
    }

    private static void toValidDescription(String name){
        if(name.isEmpty())
            throw new ArticleException("Description is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new ArticleException("Description don't be bigger than 120 characters");
    }

    public void updateDescription(String newDescription) {
        toValidDescription(newDescription);
        this.description = newDescription;
    }
}
