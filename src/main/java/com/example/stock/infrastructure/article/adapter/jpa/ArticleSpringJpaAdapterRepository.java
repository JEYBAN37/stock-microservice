package com.example.stock.infrastructure.article.adapter.jpa;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleSpringJpaAdapterRepository extends JpaRepository<ArticleEntity, Long>,
        JpaSpecificationExecutor<ArticleEntity> {

    ArticleEntity findByName(String name);
    boolean existsByName(String name);

}