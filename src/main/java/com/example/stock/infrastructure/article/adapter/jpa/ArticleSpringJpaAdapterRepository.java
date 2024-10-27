package com.example.stock.infrastructure.article.adapter.jpa;
import com.example.stock.infrastructure.article.adapter.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ArticleSpringJpaAdapterRepository extends JpaRepository<ArticleEntity, Long>,
        JpaSpecificationExecutor<ArticleEntity> {

    @Query("SELECT a FROM ArticleEntity a JOIN FETCH a.articleCategories WHERE a.id = :id")
    Optional<ArticleEntity> findByIdWithCategories(@Param("id") Long id);
    ArticleEntity findByName(String name);
    boolean existsByName(String name);




}