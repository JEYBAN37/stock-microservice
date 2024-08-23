package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.dto.command.CategoryCreateCommand;
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
        this.name = new CategoryName(name);
        this.description = new CategoryDescription(description);
    }

    public Category requestToCreate(CategoryCreateCommand categoryCreateCommand){
        this.name = new CategoryName(categoryCreateCommand.getName());
        this.description = new CategoryDescription(categoryCreateCommand.getDescription());
        return this;
    }
    public String getName() {
        return name.getName();
    }

    public String getDescription() {
        return description.getDescription();
    }
}
