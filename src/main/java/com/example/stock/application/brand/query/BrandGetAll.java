package com.example.stock.application.brand.query;


import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.service.BrandFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class BrandGetAll {
    private  final BrandFilterService brandFilterService;
    private final BrandDtoMapper brandDtoMapper;
    public List<BrandDto> execute(Integer page, Integer size, Boolean ascending) {
        int pageNumber = (page == null) ? 0 : page;
        int pageSize = (size == null) ? 10 : size;
        boolean isAscending = (ascending != null) && ascending;

        return brandFilterService.execute(pageNumber,pageSize,isAscending)
                .stream()
                .map(brandDtoMapper::toDto)
                .toList();
    }

}
