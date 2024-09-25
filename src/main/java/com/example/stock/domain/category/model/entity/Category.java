package com.example.stock.domain.category.model.entity;

import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Category {
    private Long id;
    private CategoryName name;
    private CategoryDescription description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = CategoryName.of(name);
        this.description =  CategoryDescription.of(description);
    }

    public Category requestToCreate(CategoryCreateCommand categoryCreateCommand){
        this.name =  CategoryName.of(categoryCreateCommand.getName());
        this.description =  CategoryDescription.of(categoryCreateCommand.getDescription());
        return this;
    }
    public String getName() {
        return name.getName();
    }

    public String getDescription() {
        return description.getDescription();
    }

}
