package com.example.stock.brand.branddoamintest.instance;




import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.repository.BrandRepository;

import java.util.List;
import java.util.Objects;



public class Repository implements BrandRepository {
    private final List<Brand> BrandList;
    public Repository (List<Brand> BrandList){
        this.BrandList = BrandList;
    }

    @Override
    public Brand create(Brand request) {
        Brand newBrand = new Brand((long) BrandList.size() + 1,request.getName(),request.getDescription());
        BrandList.add(newBrand);
        return newBrand;
    }

    @Override
    public void deleteById(Long id) {
        BrandList.remove(id);
    }

    @Override
    public Brand update(Brand request) {
        int num = 0;
        for (int i = 0; i < BrandList.size(); i++) {
            if (Objects.equals(BrandList.get(i).getId(), request.getId())) {
              num = i;
              System.out.println(BrandList.indexOf(BrandList.get(i)));
            }
        }
        BrandList.set(num, request);
        return request;
    }
}
