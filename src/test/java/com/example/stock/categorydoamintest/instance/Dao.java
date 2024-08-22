package com.example.stock.categorydoamintest.instance;



import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.dao.CategoryDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Dao implements CategoryDao {
    private List<Category> categoryList = new ArrayList<>();

    public Dao(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public Category getByName(String name) {
        return categoryList.stream().filter( n -> n.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Category getById(Long id) {
        categoryList.forEach(System.out::print);
        return categoryList.stream().filter( n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean nameExist(String name) {
        return categoryList.stream().anyMatch(n -> n.getName().equals(name));
    }

    @Override
    public boolean idExist(Long id) {
        return categoryList.stream().anyMatch(n -> n.getId().equals(id));
    }

    @Override
    public List<Category> getall() {
        return categoryList;
    }

    @Override
    public List<Category> getFilterAsc() {
        return categoryList.stream().sorted().toList();
    }

    @Override
    public List<Category> getFilterDsc() {
        return categoryList.stream()
                .sorted(Comparator.comparing(Category::getName).reversed())
                .toList();
    }
}
