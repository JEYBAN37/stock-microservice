package com.example.stock.domain.brand.service;


import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.port.dao.BrandDao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BrandFilterService {
    private final BrandDtoMapper brandDtoMapper;
    private final BrandDao brandDao;

    public List<BrandDto> execute(int page, int size, boolean ascending) {
        return brandDao.getAll(page, size, ascending)
                .stream()
                .map(brandDtoMapper::toDto)
                .toList();
    }

}
