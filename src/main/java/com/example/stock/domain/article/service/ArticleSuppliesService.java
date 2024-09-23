package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.dto.command.ArticleSupplyCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ArticleSuppliesService {
    private static final String MESSAGE_ERROR_UPDATE = "Article No Exist";
    private static final String MESSAGE_ERROR_LIST_EMPTY = "Supplies Empty";
    private static final String MESSAGE_ERROR_QUANTITY = "Quantity invalid";

    private final ArticleDao articleDao;
    private final ArticleRepository articleRepository;

    public void execute (ArticleSupplyCommand articleSupplyCommand) {
        if (articleSupplyCommand.getId() == null || articleSupplyCommand.getQuantity() == 0)
            throw new ArticleException(MESSAGE_ERROR_LIST_EMPTY);
        updateArticle(articleSupplyCommand);
    }

    private void updateArticle(ArticleSupplyCommand articleSupplyCommand) {
        Article articleFound = articleDao.getById(articleSupplyCommand.getId());

        if (articleFound == null)
            throw new ArticleException(MESSAGE_ERROR_UPDATE);

        validCommand(articleFound, articleSupplyCommand);
        createUpdatedArticle(articleFound, articleSupplyCommand);
    }

    private void createUpdatedArticle(Article articleFound, ArticleSupplyCommand articleSupplyCommand) {
        Article articleUpdate = new Article(
                articleFound.getId(),
                articleFound.getName(),
                articleFound.getDescription(),
                articleSupplyCommand.getQuantity() + articleFound.getQuantity(),
                articleSupplyCommand.getPrice(),
                articleFound.getBrand(),
                articleFound.getArticleCategories());
        articleRepository.update(articleUpdate);

    }

    private void validCommand (Article articleFound, ArticleSupplyCommand articleSupplyCommand){

        if (articleSupplyCommand.getQuantity() <= 0){
            throw new ArticleException(MESSAGE_ERROR_QUANTITY);
        }

        if (articleSupplyCommand.getPrice() == null ||
                String.valueOf(articleSupplyCommand.getPrice()).isEmpty()
        )
            articleSupplyCommand.setPrice(articleFound.getPrice());
    }

}
