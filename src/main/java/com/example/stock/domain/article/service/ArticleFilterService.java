package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import lombok.AllArgsConstructor;
import java.util.List;
import static com.example.stock.domain.static_variables.StaticData.MESSAGE_PAGE_VALID;


@AllArgsConstructor
public class ArticleFilterService {
    private final ArticleDao articleDao;
    public List<Article> execute (int page, int size, boolean ascending, String byName,
                                  String byBrand, String byCategory){
        if (page < 0 || size <= 0) {
            throw new ArticleException(MESSAGE_PAGE_VALID);
        }
        return articleDao.getAll(page, size, ascending, byName, byBrand, byCategory);
    }
}
