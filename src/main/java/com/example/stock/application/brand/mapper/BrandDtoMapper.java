package com.example.stock.application.brand.mapper;


import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BrandDtoMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BrandDto toDto (Brand objectOfdomain);
}
