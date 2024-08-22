package com.example.stock.domain.category.model.entity;

import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Category {
    private CategoryId id;
    private CategoryName name;
    private CategoryDescription description;

    public Category(Long id, String name, String description) {
        this.id = new CategoryId(id);
        this.name = new CategoryName(name);
        this.description = new CategoryDescription(description);
    }

    public Category requestToCreate(CategoryCreateCommand categoryCreateCommand){
        this.name = new CategoryName(categoryCreateCommand.getName());
        this.description = new CategoryDescription(categoryCreateCommand.getDescription());
        return this;
    }
    public Long getId() {
        return id.getId();
    }

    public String getName() {
        return name.getName();
    }

    public String getDescription() {
        return description.getDescription();
    }
}
