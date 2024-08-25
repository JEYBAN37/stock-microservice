package com.example.stock.brand.instance;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;

public class BrandDtoMapperInstance implements BrandDtoMapper {
    @Override
    public BrandDto toDto(Brand domain) {
        if (domain == null) {
            return null;
        }

        BrandDto dto = new BrandDto();
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());

        return dto;
    }
}
