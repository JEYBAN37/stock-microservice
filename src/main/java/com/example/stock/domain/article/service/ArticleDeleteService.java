package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ArticleDeleteService {
    private final ArticleRepository articleRepository;
    private final ArticleDao articleDao;
    private static final String MESSAGE_ERROR_REMOVE = "Error To Remove No Exist";

    public void execute(Long id){
        if (!articleDao.idExist(id))
            throw new ArticleException(MESSAGE_ERROR_REMOVE);
        articleRepository.deleteById(id);
    }
}
