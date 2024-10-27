package com.example.stock.infrastructure.article.adapter.jpa.dao;

import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;


import java.util.ArrayList;
import java.util.List;

import static com.example.stock.domain.static_variables.StaticData.*;


@AllArgsConstructor
public class ArticleSpecification {
    private final String byName;
    private final String byBrand;
    private final String byCategory;

    public static Specification<ArticleEntity> hasIdIn(List<Long> ids) {
        return (root, query, criteriaBuilder) -> root.get("id").in(ids);
    }

    public Specification<ArticleEntity> toSpecification() {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (byName != null && !byName.isEmpty()) {
                predicates.add(
                        criteriaBuilder.like(root.get(NAME), byName + "%")
                );
            }

            if (byBrand != null && !byBrand.isEmpty()) {
                predicates.add(
                        criteriaBuilder.like(root.get(BRAND).get(NAME), "%" + byBrand + "%")
                );
            }

            if (byCategory != null && !byCategory.isEmpty()) {
                Join<ArticleEntity, CategoryEntity> categoryJoin = root.join(ARTICLE_CATEGORY);
                predicates.add(
                        criteriaBuilder.like(categoryJoin.get(NAME), "%" + byCategory + "%")
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}