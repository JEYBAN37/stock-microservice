package com.example.stock.domain.brand.port.dao;



import com.example.stock.domain.brand.model.entity.Brand;

import java.util.List;

public interface BrandDao {
    Brand getByName(String name);
    Brand getById(Long id);
    boolean nameExist(String name);
    boolean idExist(Long id);
    List<Brand> getAll(int page, int size, boolean ascending);

}
