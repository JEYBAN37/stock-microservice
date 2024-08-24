package com.example.stock.brand.instance;


import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.entity.Category;

public class BrandDtoMapperInstance implements BrandDtoMapper {
    @Override
    public BrandDto toDto(Brand domain) {
        if (domain == null) {
            return null;
        }

        BrandDto dto = new BrandDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());

        return dto;
    }
}
