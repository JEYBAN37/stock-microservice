package com.example.stock.infrastructure.article.adapter.entity;


import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "articles", indexes = {
        @Index(name = "idx_articles_name", columnList = "name"),
        @Index(name = "idx_articles_quantity", columnList = "quantity"),
        @Index(name = "idx_articles_price", columnList = "price")

})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "articles_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private CategoryEntity[] articleCategories;
}
