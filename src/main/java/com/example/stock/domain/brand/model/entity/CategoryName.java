package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.CategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class CategoryName {
    private static final int MAXIMUM_ALLOW_LETTERS = 50;
    String name;

    public CategoryName(String name) {
        toValidName(name);
        this.name = name;
    }

    private void toValidName(String name){
        if(name.isEmpty())
            throw new CategoryException("Name is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new CategoryException("Name don't be bigger than 50 characters");
    }
}
