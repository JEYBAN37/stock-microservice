package com.example.stock.infrastructure.category.adapter.mapper;

import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryDboMapper {

public CategoryEntity toDatabase (Category domain){
    if(domain == null){
        return null;
    }
    return new CategoryEntity(
            domain.getId(),
            domain.getName(),
            domain.getDescription());
}

    public Category toDomain(CategoryEntity entity){
        if(entity == null){
            return null;
        }

        return new Category(entity.getId(), entity.getName(), entity.getDescription());
    }
}
