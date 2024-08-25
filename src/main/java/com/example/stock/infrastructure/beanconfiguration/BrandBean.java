package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import com.example.stock.domain.brand.service.BrandCreateService;
import com.example.stock.domain.brand.service.BrandDeleteService;
import com.example.stock.domain.brand.service.BrandFilterService;
import com.example.stock.domain.brand.service.BrandUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandBean {
    @Bean
    public BrandCreateService brandCreateService (BrandRepository brandRepository, BrandDao brandDao){
        return new BrandCreateService(brandRepository,brandDao);
    }
    @Bean
    public BrandDeleteService brandDeleteService (BrandRepository brandRepository, BrandDao brandDao){
        return new BrandDeleteService(brandRepository,brandDao);
    }
    @Bean
    public BrandUpdateService brandUpdateService(BrandRepository brandRepository, BrandDao brandDao){
        return new BrandUpdateService(brandRepository, brandDao);
    }
    @Bean
    public BrandFilterService brandFilterService(BrandDtoMapper brandDtoMapper, BrandDao brandDao){
        return new BrandFilterService(brandDtoMapper,brandDao);
    }

}

