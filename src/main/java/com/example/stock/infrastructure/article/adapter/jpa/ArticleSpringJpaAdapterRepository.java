package com.example.stock.infrastructure.article.adapter.jpa;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleSpringJpaAdapterRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity findByName(String name);
    boolean existsByName(String name);
    List<ArticleEntity>  findAllByOrderByNameAsc();
    List<ArticleEntity> findAllByOrderByNameDesc();
}