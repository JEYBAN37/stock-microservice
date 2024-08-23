package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BrandDescription {
    private static final int MAXIMUM_ALLOW_LETTERS = 90;
    String description;

    public BrandDescription(String description){
        toValidDescription(description);
        this.description = description;
    }
    private void toValidDescription(String name){
        if(name.isEmpty())
            throw new BrandException("Description is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new BrandException("Description don't be bigger than 120 characters");
    }
}
