package com.example.stock.application.brand.query;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.port.dao.BrandDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrandById {
    private final BrandDao brandDao;
    private final BrandDtoMapper brandDtoMapper;

    public BrandDto execute (Long id){return brandDtoMapper.toDto((brandDao.getById(id)));}
}
