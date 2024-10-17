package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import com.example.stock.domain.brand.service.BrandCreateService;
import com.example.stock.domain.brand.service.BrandFilterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandBean {
    @Bean
    public BrandCreateService brandCreateService (BrandRepository brandRepository, BrandDao brandDao){
        return new BrandCreateService(brandRepository,brandDao);
    }
    @Bean
    public BrandFilterService brandFilterService( BrandDao brandDao){
        return new BrandFilterService(brandDao);
    }


}

