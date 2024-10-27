package com.example.stock.domain.article.port.dao;



import com.example.stock.domain.article.model.entity.Article;

import java.util.List;

public interface ArticleDao {
    Article getByName(String name);
    Article getById(Long id);
    List<Article> getAllBySales(List<Long> ids);
    List<Article> getAllByIds (List<Long> ids, int page, int size, boolean ascending,String byName,String byBrand,String byCategory);
    boolean nameExist(String name);
    boolean idExist(Long id);
    List<Article> getAll(int page, int size, boolean ascending,String byName,String byBrand,String byCategory);

}
