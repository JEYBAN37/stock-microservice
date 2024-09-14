package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.NAME_MANDATORY;
import static com.example.stock.domain.static_variables.StaticData.NAME_MAX_ERROR;

@NoArgsConstructor
@Getter
public class BrandName {
    private static final int MAXIMUM_ALLOW_LETTERS = 50;

    String name;

    private BrandName(String name) {
        this.name = name;
    }

    public static BrandName of(String name) {
        toValidName(name);
        return new BrandName(name);
    }

    private static void toValidName(String name){
        if(name == null || name.isEmpty())
            throw new BrandException(NAME_MANDATORY);
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new BrandException(NAME_MAX_ERROR);
    }
}
