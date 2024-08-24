package com.example.stock.infrastructure.brand.adapter.mapper;

import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrandDboMapper {

public BrandEntity toDatabase (Brand domain){
    if(domain == null){
        return null;
    }
    return new BrandEntity(
            domain.getId(),
            domain.getName(),
            domain.getDescription());
}

    public Brand toDomain(BrandEntity entity){
        if(entity == null){
            return null;
        }

        return new Brand(entity.getId(), entity.getName(), entity.getDescription());
    }



}
