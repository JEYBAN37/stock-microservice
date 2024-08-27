package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BrandName {
    private static final int MAXIMUM_ALLOW_LETTERS = 50;
    private static final String NAME_MANDATORY = "Name is mandatory";
    private static final String DESCRIPTION_MAX_ERROR ="Name don't be bigger than 50 characters";

    String name;

    private BrandName(String name) {
        this.name = name;
    }

    public static BrandName of(String name) {
        toValidName(name);
        return new BrandName(name);
    }

    private static void toValidName(String name){
        if(name.isEmpty())
            throw new BrandException(NAME_MANDATORY);
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new BrandException(DESCRIPTION_MAX_ERROR);
    }
}
