package com.example.stock.application.brand.command;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.service.BrandCreateService;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.service.CategoryCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandCreateHandler {
    private final BrandCreateService brandCreateService;
    private final BrandDtoMapper brandDtoMapper;

    @Autowired
    public BrandCreateHandler(BrandCreateService brandCreateService, BrandDtoMapper brandDtoMapper) {
        this.brandCreateService = brandCreateService;
        this.brandDtoMapper = brandDtoMapper;
    }

    public BrandDto execute (BrandCreateCommand createCommand){
        return brandDtoMapper.toDto(brandCreateService.execute(createCommand));
    }
}
