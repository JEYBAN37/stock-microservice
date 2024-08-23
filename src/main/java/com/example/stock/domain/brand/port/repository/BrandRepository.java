package com.example.stock.domain.brand.port.repository;


import com.example.stock.domain.brand.model.entity.Brand;

public interface BrandRepository {
    Brand create (Brand request);
    void deleteById (Long id);
    Brand update (Brand request);
}
