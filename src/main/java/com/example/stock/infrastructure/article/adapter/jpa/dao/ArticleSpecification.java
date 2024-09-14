package com.example.stock.infrastructure.article.adapter.jpa.dao;

import org.springframework.data.jpa.domain.Specification;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;



public class ArticleSpecification {

    private final String byName;
    private final String byBrand;
    private final String byCategory;

    public ArticleSpecification(String byName, String byBrand, String byCategory) {
        this.byName = byName;
        this.byBrand = byBrand;
        this.byCategory = byCategory;
    }

    public Specification<ArticleEntity> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (byName != null && !byName.isEmpty()) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(root.get("name"), byName + "%"));
            }

            if (byBrand != null && !byBrand.isEmpty()) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("brand").get("name"), byBrand));
            }

            if (byCategory != null && !byCategory.isEmpty()) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(root.join("articleCategories").get("name"),
                                "%" + byCategory + "%"));
            }

            return predicates;
        };
    }
}