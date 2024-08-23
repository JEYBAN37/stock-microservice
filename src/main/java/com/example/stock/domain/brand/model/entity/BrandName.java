package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BrandName {
    private static final int MAXIMUM_ALLOW_LETTERS = 50;
    String name;

    public BrandName(String name) {
        toValidName(name);
        this.name = name;
    }

    private void toValidName(String name){
        if(name.isEmpty())
            throw new BrandException("Name is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new BrandException("Name don't be bigger than 50 characters");
    }
}
