package com.example.stock.domain.article.model.entity;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
public class Article {
    private Long id;
    private ArticleName name;
    private ArticleDescription description;
    private ArticleQuantity quantity;
    private ArticlePrice price;
    private Brand brand;
    private List<Category> articleCategories;


    public Article(Long id, String name, String description, int quantity,
                   BigDecimal price, Brand brand,  List<Category> articleCategories)
    {
        this.id = id;
        this.name = ArticleName.of(name);
        this.description = ArticleDescription.of(description);
        this.quantity = ArticleQuantity.of(quantity);
        this.price =  ArticlePrice.of(price);
        this.articleCategories = articleCategories;
        this.brand = brand;
    }
    public Article requestToCreate(ArticleCreateCommand articleCreateCommand,
                                   List<Category> articleCategories, Brand brand)
    {
        this.name = ArticleName.of(articleCreateCommand.getName());
        this.description = ArticleDescription.of(articleCreateCommand.getDescription());
        this.quantity = ArticleQuantity.of(articleCreateCommand.getQuantity());
        this.price = ArticlePrice.of(articleCreateCommand.getPrice());
        this.articleCategories = articleCategories;
        this.brand = brand;
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
