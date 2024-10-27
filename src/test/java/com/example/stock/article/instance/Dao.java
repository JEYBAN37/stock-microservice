package com.example.stock.article.instance;




import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.dao.ArticleDao;


import java.util.ArrayList;
import java.util.List;


public class Dao implements ArticleDao {
    private List<Article> ArticleList = new ArrayList<>();

    public Dao(List<Article> ArticleList) {
        this.ArticleList = ArticleList;
    }

    @Override
    public Article getByName(String name) {
        return ArticleList.stream().filter( n -> n.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Article getById(Long id) {
        ArticleList.forEach(System.out::print);
        return ArticleList.stream().filter( n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Article> getAllBySales(List<Long> ids) {
        return List.of();
    }

    @Override
    public List<Article> getAllByIds(List<Long> ids, int page, int size, boolean ascending, String byName, String byBrand, String byCategory) {
        return List.of();
    }


    @Override
    public boolean nameExist(String name) {
        return ArticleList.stream().anyMatch(n -> n.getName().equals(name));
    }

    @Override
    public boolean idExist(Long id) {
        return ArticleList.stream().anyMatch(n -> n.getId().equals(id));
    }

    @Override
    public List<Article> getAll(int page, int size, boolean ascending, String byName, String byBrand, String byCategory) {
        return null;
    }

}
