package com.example.stock.domain.brand.port.repository;


import com.example.stock.domain.brand.model.entity.Category;

public interface CategoryRepository {
    Category create (Category request);
    void deleteById (Long id);
    Category update (Category request);
}
