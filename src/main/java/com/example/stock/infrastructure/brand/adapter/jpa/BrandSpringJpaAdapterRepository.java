package com.example.stock.infrastructure.brand.adapter.jpa;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandSpringJpaAdapterRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findByName(String name);
    boolean existsByName(String name);

}