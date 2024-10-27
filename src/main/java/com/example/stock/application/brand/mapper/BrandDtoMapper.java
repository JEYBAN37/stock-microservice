package com.example.stock.application.brand.mapper;


import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandDtoMapper {
    BrandDto toDto (Brand objectOfdomain);
}
