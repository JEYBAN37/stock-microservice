package com.example.stock.domain.article.model.entity;

import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class Article {
    private Long id;
    private ArticleName name;
    private ArticleDescription description;
    private ArticleQuantity quantity;
    private ArticlePrice price;

    public Article(Long id, String name, String description,int quantity,BigDecimal price) {
        this.id = id;
        this.name = new ArticleName(name);
        this.description = new ArticleDescription(description);
        this.quantity = new ArticleQuantity(quantity);
        this.price = new ArticlePrice(price);
    }

    public Article requestToCreate(ArticleCreateCommand articleCreateCommand){
        this.name = new ArticleName(articleCreateCommand.getName());
        this.description = new ArticleDescription(articleCreateCommand.getDescription());
        this.quantity = new ArticleQuantity(articleCreateCommand.getQuantity());
        this.price = new ArticlePrice(articleCreateCommand.getPrice());
        return this;
    }
    public String getName() {
        return name.getName();
    }
    public String getDescription() {
        return description.getDescription();
    }
    public int getQuantity (){return quantity.getQuantity();}
    public BigDecimal getPrice() {
        return price.getPrice();
    }
}
