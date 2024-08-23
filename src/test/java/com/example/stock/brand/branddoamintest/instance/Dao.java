package com.example.stock.brand.branddoamintest.instance;




import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;

import java.util.ArrayList;
import java.util.List;


public class Dao implements BrandDao {
    private List<Brand> BrandList = new ArrayList<>();

    public Dao(List<Brand> BrandList) {
        this.BrandList = BrandList;
    }

    @Override
    public Brand getByName(String name) {
        return BrandList.stream().filter( n -> n.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Brand getById(Long id) {
        BrandList.forEach(System.out::print);
        return BrandList.stream().filter( n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean nameExist(String name) {
        return BrandList.stream().anyMatch(n -> n.getName().equals(name));
    }

    @Override
    public boolean idExist(Long id) {
        return BrandList.stream().anyMatch(n -> n.getId().equals(id));
    }

    @Override
    public List<Brand> getAll(int page, int size, boolean ascending) {
        return BrandList;
    }

}
