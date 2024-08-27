package com.example.stock.article.instance;


import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.repository.ArticleRepository;

import java.util.List;
import java.util.Objects;



public class Repository implements ArticleRepository {
    private final List<Article> ArticleList;
    public Repository (List<Article> ArticleList){
        this.ArticleList = ArticleList;
    }

    @Override
    public Article create(Article request) {
        Article newArticle = new Article((long) ArticleList.size() + 1,request.getName(),request.getDescription(),request.getQuantity(),request.getPrice(),request.getBrand(),request.getArticleCategories());
        ArticleList.add(newArticle);
        return newArticle;
    }

    @Override
    public void deleteById(Long id) {
        ArticleList.remove(id);
    }

    @Override
    public Article update(Article request) {
        int num = 0;
        for (int i = 0; i < ArticleList.size(); i++) {
            if (Objects.equals(ArticleList.get(i).getId(), request.getId())) {
              num = i;
              System.out.println(ArticleList.indexOf(ArticleList.get(i)));
            }
        }
        ArticleList.set(num, request);
        return request;
    }
}
