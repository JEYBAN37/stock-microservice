package com.example.stock.category.instance;


import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;



public class Repository implements CategoryRepository {
    private final List<Category> categoryList;
    public Repository (List<Category> categoryList){
        this.categoryList = categoryList;
    }

    @Override
    public Category create(Category request) {
        Category newCategory = new Category((long) categoryList.size() + 1,request.getName(),request.getDescription());
        categoryList.add(newCategory);
        return newCategory;
    }

    @Override
    public void deleteById(Long id) {
        categoryList.remove(id);
    }

    @Override
    public Category update(Category request) {
        int num = 0;
        for (int i = 0; i < categoryList.size(); i++) {
            if (Objects.equals(categoryList.get(i).getId(), request.getId())) {
              num = i;
              System.out.println(categoryList.indexOf(categoryList.get(i)));
            }
        }
        categoryList.set(num, request);
        return request;
    }
}
