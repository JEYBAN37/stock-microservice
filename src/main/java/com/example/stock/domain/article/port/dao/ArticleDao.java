package com.example.stock.domain.article.port.dao;



import com.example.stock.domain.article.model.entity.Article;

import java.util.List;

public interface ArticleDao {
    Article getByName(String name);
    Article getById(Long id);
    boolean nameExist(String name);
    boolean idExist(Long id);
    List<Article> getAll(int page, int size, boolean ascending);

}
