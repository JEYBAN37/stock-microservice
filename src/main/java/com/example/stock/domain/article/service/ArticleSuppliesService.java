package com.example.stock.domain.article.service;

import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArticleSuppliesService {
    private static final String MESSAGE_ERROR_UPDATE = "Article No Exist";
    private static final String MESSAGE_ERROR_LIST_EMPTY = "List Supplies Empty";
    private static final String MESSAGE_ERROR_QUANTITY = "Quantity invalid";
    private static final String MESSAGE_ERROR_QUANTITY_MANDALORY = "Quantity Mandalory";

    private final ArticleDao articleDao;
    private final ArticleRepository articleRepository;

    public List<Article> execute (List<ArticleEditCommand> articleEditCommands) {

        if (articleEditCommands.isEmpty())
            throw new ArticleException(MESSAGE_ERROR_LIST_EMPTY);

        return articleEditCommands.stream()
                .map(this::updateArticle)
                .toList();
    }

    private Article updateArticle(ArticleEditCommand articleCommand) {
        Article articleFound = articleDao.getById(articleCommand.getId());
        validCommand(articleFound,articleCommand);
        return createUpdatedArticle(articleFound, articleCommand);
    }

    private Article createUpdatedArticle(Article articleFound, ArticleEditCommand articleCommand) {
        Article articleUpdate = new Article(
                articleFound.getId(),
                articleFound.getName(),
                articleFound.getDescription(),
                articleCommand.getQuantity() + articleFound.getQuantity(),
                articleCommand.getPrice(),
                articleFound.getBrand(),
                articleFound.getArticleCategories());
        return articleRepository.update(articleUpdate);

    }

    private void validCommand (Article articleFound, ArticleEditCommand articleCommand){
        if (articleFound == null) {
            throw new ArticleException(MESSAGE_ERROR_UPDATE);
        }

        if (String.valueOf(articleCommand.getQuantity() ) == null){
            throw new ArticleException(MESSAGE_ERROR_QUANTITY_MANDALORY);
        }

        if (articleCommand.getQuantity() <= 0 ||
                String.valueOf(articleCommand.getQuantity()).isEmpty()){
            throw new ArticleException(MESSAGE_ERROR_QUANTITY);
        }

        if (articleCommand.getPrice() == null ||
                String.valueOf(articleCommand.getPrice()).isEmpty())
            articleCommand.setPrice(articleFound.getPrice());
    }

}
