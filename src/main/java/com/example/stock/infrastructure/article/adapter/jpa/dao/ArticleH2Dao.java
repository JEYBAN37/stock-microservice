package com.example.stock.infrastructure.article.adapter.jpa.dao;

import com.example.stock.domain.article.model.constant.ArticleConstant;
import com.example.stock.domain.article.model.entity.Article;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.port.dao.ArticleDao;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import com.example.stock.infrastructure.article.adapter.jpa.ArticleSpringJpaAdapterRepository;

import com.example.stock.infrastructure.article.adapter.mapper.ArticleDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.stock.domain.static_variables.StaticData.NAME;

@AllArgsConstructor
@Repository
public class ArticleH2Dao implements ArticleDao {

    private final ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;
    private final ArticleDboMapper articleDboMapper;

    @Override
    public Article getByName(String name) {
        Optional<ArticleEntity> optionalArticle = Optional.ofNullable(
                articleSpringJpaAdapterRepository.findByName(name));

        if (optionalArticle.isEmpty()){
            throw new ArticleException(String.format(ArticleConstant.TASK_NOT_FOUND_MESSAGE_ERROR, name));
        }
        return articleDboMapper.toDomain(optionalArticle.get());
    }

    @Override
    public Article getById(Long id) {
        Optional<ArticleEntity> optionalArticle = articleSpringJpaAdapterRepository.findByIdWithCategories(id);
        return optionalArticle.map(articleDboMapper::toDomain).orElse(null);

    }

    @Override
    public List<Article> getAllByIds(List<Long> ids) {
        return articleSpringJpaAdapterRepository.findByIds(ids)
                .stream()
                .map(articleDboMapper::toDomain)
                .toList();
    }

    @Override
    public List<Article> getAll(int page, int size, boolean ascending, String byName, String byBrand,
                                String byCategory)
    {
        ArticleSpecification spec = new ArticleSpecification(byName,byBrand,byCategory);
        Specification<ArticleEntity> specification = spec.toSpecification();

        PageRequest pageable = PageRequest.of(page, size, Sort
                .by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, NAME));
        return articleSpringJpaAdapterRepository.findAll(specification, pageable)
                .stream()
                .map(articleDboMapper::toDomain)
                .toList();
    }

    @Override
    public boolean nameExist(String name) {
        return articleSpringJpaAdapterRepository.existsByName(name);
    }

    @Override
    public boolean idExist(Long id) {
        return articleSpringJpaAdapterRepository.existsById(id);
    }

}
