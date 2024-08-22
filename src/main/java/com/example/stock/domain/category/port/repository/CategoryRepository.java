package com.example.stock.domain.category.port.repository;


import com.example.stock.domain.category.model.entity.Category;

public interface CategoryRepository {
    Category create (Category request);
    void deleteById (Long id);
    Category update (Category request);
}
