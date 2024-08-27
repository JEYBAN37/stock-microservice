package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Brand {
    private Long id;
    private BrandName name;
    private BrandDescription description;

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = BrandName.of(name);
        this.description = new BrandDescription(description);
    }

    public Brand requestToCreate(BrandCreateCommand brandCreateCommand){
        this.name =BrandName.of(brandCreateCommand.getName());
        this.description = BrandDescription.of(brandCreateCommand.getDescription());
        return this;
    }
    public String getName() {
        return name.getName();
    }

    public String getDescription() {
        return description.getDescription();
    }
}
