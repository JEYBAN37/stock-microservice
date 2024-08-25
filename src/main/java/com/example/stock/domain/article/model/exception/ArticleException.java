package com.example.stock.domain.article.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor(force = true)
@Getter
@Setter
public class ArticleException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorMessage;
    public ArticleException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
