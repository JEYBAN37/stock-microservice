package com.example.stock.domain.article.service;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.category.service.CategoryListArticle;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleUpdateService {
    private final ArticleRepository articleRepository;
    private final ArticleDao articleDao;
    private final CategoryListArticle categoryListArticle;
    private final BrandDao brandDao;

    private static final String MESSAGE_ERROR_UPDATE = "Article No Exist";
    private static final String MESSAGE_ERROR_ADD = "Article With This Name Exist";
    private static final String MESSAGE_ERROR_BRAND_NOT = "Brand Inject Not Found";

    public Article execute(Long id, ArticleEditCommand articleEditCommand) {

        Article currentCategory = articleDao.getById(id);
        validateParams(articleEditCommand,currentCategory);
        Brand brandArticle = brandDao.getById(articleEditCommand.getBrand());
        if (brandArticle == null)
            throw new ArticleException(MESSAGE_ERROR_BRAND_NOT);

        Article articleUpdate = new Article(
                currentCategory.getId(),
                articleEditCommand.getName(),
                articleEditCommand.getDescription(),
                articleEditCommand.getQuantity(),
                articleEditCommand.getPrice(),
                brandArticle,
                categoryListArticle.execute(articleEditCommand.getArticleCategories())
        );
        return articleRepository.update(articleUpdate);
    }

    private void validateParams (ArticleEditCommand articleEditCommand,Article currentCategory){

        if (currentCategory == null) {
            throw new ArticleException(MESSAGE_ERROR_UPDATE);
        }
        if (!currentCategory.getName().equals(articleEditCommand.getName()) &&
                articleDao.nameExist(articleEditCommand.getName())) {
            throw new ArticleException(MESSAGE_ERROR_ADD);
        }
        if (articleEditCommand.getBrand() == null)
            throw new ArticleException(MESSAGE_ERROR_BRAND_NOT);
    }
}
