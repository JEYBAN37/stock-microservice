package com.example.stock.brand.branddoamintest.instance;


import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.entity.Category;

public class BrandDtoMapperInstance implements CategoryDtoMapper {
    @Override
    public CategoryDto toDto(Category domain) {
        if (domain == null) {
            return null;
        }

        CategoryDto dto = new CategoryDto();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());

        return dto;
    }
}
