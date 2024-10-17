package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.dao.ArticleDao;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;


@AllArgsConstructor
public class ArticleGetByIdsService {
    private final ArticleDao articleDao;

    public List<Article> execute (List<Long> ids){
        if(ids.isEmpty())
            return Collections.emptyList();
        return articleDao.getAllByIds(ids);

    }
}
