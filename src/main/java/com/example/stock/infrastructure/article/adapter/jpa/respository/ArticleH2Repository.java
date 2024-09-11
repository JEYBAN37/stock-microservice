package com.example.stock.infrastructure.article.adapter.jpa.respository;

import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.port.repository.ArticleRepository;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import com.example.stock.infrastructure.article.adapter.jpa.ArticleSpringJpaAdapterRepository;
import com.example.stock.infrastructure.article.adapter.mapper.ArticleDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class ArticleH2Repository implements ArticleRepository {
    private final ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;
    private final ArticleDboMapper articleDboMapper;
    @Override
    public Article create(Article request) {
        ArticleEntity articleToSave = articleDboMapper.toDatabase(request);
        ArticleEntity articleSaved = articleSpringJpaAdapterRepository.save(articleToSave);
        return articleDboMapper.toDomain(articleSaved);
    }
    @Override
    public void deleteById(Long id) {
        articleSpringJpaAdapterRepository.deleteById(id);
    }
    @Override
    public Article update(Article request) {
        ArticleEntity articleToUpdate = articleDboMapper.toDatabase(request);
        ArticleEntity articleUpdated = articleSpringJpaAdapterRepository.save(articleToUpdate);
        return articleDboMapper.toDomain(articleUpdated);
    }
}
