package com.example.stock.domain.brand.port.dao;



import com.example.stock.domain.brand.model.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category getByName(String name);
    Category getById(Long id);
    boolean nameExist(String name);
    boolean idExist(Long id);
    List<Category> getAll(int page, int size, boolean ascending);

}
