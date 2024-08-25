package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleUpdateService {
    private final ArticleRepository articleRepository;
    private final ArticleDao articleDao;
    private static final String MESSAGE_ERROR_UPDATE = "Article No Exist";
    public Article execute(ArticleEditCommand articleEditCommand, Long id) {
        Article currentCategory = articleDao.getById(id);
        if (currentCategory == null)
            throw new ArticleException(MESSAGE_ERROR_UPDATE);
        Article categoryUpdate = new Article(
                currentCategory.getId(),
                articleEditCommand.getName(),
                articleEditCommand.getDescription(),
                articleEditCommand.getQuantity(),
                articleEditCommand.getPrice()
        );
        return articleRepository.update(categoryUpdate);
    }
}
