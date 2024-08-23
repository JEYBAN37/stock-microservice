package com.example.stock.infrastructure.category.adapter.jpa;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorySpringJpaAdapterRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);
    boolean existsByName(String name);
    List<CategoryEntity>  findAllByOrderByNameAsc();
    List<CategoryEntity> findAllByOrderByNameDesc();
}