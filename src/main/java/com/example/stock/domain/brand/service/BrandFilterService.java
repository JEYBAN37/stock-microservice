package com.example.stock.domain.brand.service;


import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.port.dao.BrandDao;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.example.stock.domain.static_variables.StaticData.MESSAGE_PAGE_VALID;

@AllArgsConstructor
public class BrandFilterService {
    private final BrandDtoMapper brandDtoMapper;
    private final BrandDao brandDao;

    public List<BrandDto> execute(int page, int size, boolean ascending) {
        if (page < 0 || size <= 0) {
            throw new ArticleException(MESSAGE_PAGE_VALID);
        }
        return brandDao.getAll(page, size, ascending)
                .stream()
                .map(brandDtoMapper::toDto)
                .toList();
    }
}
