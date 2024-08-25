package com.example.stock.infrastructure.article.adapter.entity;


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
}
