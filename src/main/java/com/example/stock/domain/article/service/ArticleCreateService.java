package com.example.stock.domain.article.service;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.category.service.CategoryListArticle;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleCreateService {
    private final ArticleRepository articleRepository;
    private final  ArticleDao articleDao;
    private final CategoryListArticle categoryListArticle;
    private final BrandDao brandDao;

    private static final String MESSAGE_ERROR_ADD = "Article Exist";
    private static final String MESSAGE_ERROR_BRAND = "Brand Not Found";
    private static final String MESSAGE_ERROR_BRAND_NOT = "Brand Inject Not Found";

    public Article execute (ArticleCreateCommand createCommand){

        validateParams(createCommand);
        Brand brandArticle = brandDao.getById(createCommand.getBrand());

        if (brandArticle == null)
            throw new ArticleException(MESSAGE_ERROR_BRAND_NOT);

        Article articleToCreate = new Article().requestToCreate(
                createCommand,
                categoryListArticle.execute(createCommand.getArticleCategories()),
                brandArticle
        );

        return articleRepository.create(articleToCreate);
    }

private void validateParams (ArticleCreateCommand createCommand){

    if (createCommand.getId() != null && articleDao.idExist(createCommand.getId()))
        throw new ArticleException(MESSAGE_ERROR_ADD);

    if (articleDao.nameExist(createCommand.getName()))
        throw new ArticleException(MESSAGE_ERROR_ADD);

    if (createCommand.getBrand() == null)
        throw new ArticleException(MESSAGE_ERROR_BRAND);
}


}
