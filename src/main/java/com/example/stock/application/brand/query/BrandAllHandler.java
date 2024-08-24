package com.example.stock.application.brand.query;


import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.service.BrandFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class BrandAllHandler {
    private  final BrandFilterService brandFilterService;
    public List<BrandDto> execute(int page, int size, boolean ascending) {
        return brandFilterService.execute(page,size,ascending);
    }

}
