package com.example.stock.domain.article.port.repository;


import com.example.stock.domain.article.model.entity.Article;

public interface ArticleRepository {
    Article create (Article request);
    void deleteById (Long id);
    Article update (Article request);
}
