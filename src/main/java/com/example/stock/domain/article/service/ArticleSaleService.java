package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.dto.command.ArticleSaleCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
public class ArticleSaleService {
    private final ArticleDao articleDao;
    private final ArticleRepository articleRepository;

    public List<Article> execute (List<ArticleSaleCommand> articleSaleCommands){
        if(articleSaleCommands.isEmpty())
            return Collections.emptyList();
        List<Article> articlesValid = articleDao.getAllBySales(articleSaleCommands.stream().map(ArticleSaleCommand::getId).toList());

        List<Article> toUpdate = getArticles(articleSaleCommands, articlesValid);

        if(toUpdate.isEmpty())
            return Collections.emptyList();

        return toUpdate
                .stream()
                .filter(Objects::nonNull)
                .map(articleRepository::update)
                .toList();
    }

    private List<Article> getArticles(List<ArticleSaleCommand> articleSaleCommands, List<Article> articlesValid){
        return articleSaleCommands.stream()
                .map(articleSaleCommand -> {
                    Article article = articlesValid.stream()
                            .filter(article1 -> article1.getId().equals(articleSaleCommand.getId()))
                            .findFirst()
                            .orElse(null);

                    if(article == null)
                        return null;

                    if (article.getQuantity() < articleSaleCommand.getQuantity())
                        return null;

                    article.setQuantity(article.getQuantity() - articleSaleCommand.getQuantity());
                    return article;
                })
                .toList();
    }
}
