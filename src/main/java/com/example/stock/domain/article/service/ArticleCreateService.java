package com.example.stock.domain.article.service;


import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleCreateService {
    private final ArticleRepository articleRepository;
    private final ArticleDao articleDao;

    private static final String MESSAGE_ERROR_ADD = "Article Exist";
    public Article execute (ArticleCreateCommand articleCreateCommand){
        if (articleDao.nameExist(articleCreateCommand.getName()))
            throw new ArticleException(MESSAGE_ERROR_ADD);
        Article articleToCreate = new Article().requestToCreate(articleCreateCommand);
        return articleRepository.create(articleToCreate);
    }
}
