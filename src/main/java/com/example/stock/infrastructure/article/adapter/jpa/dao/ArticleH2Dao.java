package com.example.stock.infrastructure.article.adapter.jpa.dao;

import com.example.stock.domain.article.model.constant.ArticleConstant;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import com.example.stock.infrastructure.article.adapter.jpa.ArticleSpringJpaAdapterRepository;

import com.example.stock.infrastructure.article.adapter.mapper.ArticleDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ArticleH2Dao implements ArticleDao {

    private final ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;
    private final ArticleDboMapper articleDboMapper;

    @Override
    public Article getByName(String name) {
        Optional<ArticleEntity> optionalArticle = Optional.ofNullable(articleSpringJpaAdapterRepository.findByName(name));
        if (optionalArticle.isEmpty()){
            throw new ArticleException(String.format(ArticleConstant.TASK_NOT_FOUND_MESSAGE_ERROR, name));
        }
        return articleDboMapper.toDomain(optionalArticle.get());
    }

    @Override
    public Article getById(Long id) {
        Optional<ArticleEntity> optionalArticle = articleSpringJpaAdapterRepository.findById(id);
        if (optionalArticle.isEmpty()){
            throw new ArticleException(String.format(ArticleConstant.TASK_NOT_FOUND_MESSAGE_ERROR, id));
        }
        return articleDboMapper.toDomain(optionalArticle.get());
    }

    @Override
    public boolean nameExist(String name) {
        return articleSpringJpaAdapterRepository.existsByName(name);
    }

    @Override
    public boolean idExist(Long id) {
        return articleSpringJpaAdapterRepository.existsById(id);
    }

    @Override
    public List<Article> getAll(int page, int size, boolean ascending, String byName, String byBrand, String byCategory) {
        return null;
    }

}
