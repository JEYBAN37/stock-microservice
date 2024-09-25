package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;

@NoArgsConstructor
@Getter
public class BrandName {
    String name;
    private BrandName(String name) {
        this.name = name;
    }

    public static BrandName of(String name) {

        return new BrandName(toValidName(name));
    }

    private static String toValidName(String name){
        if(name == null || name.isEmpty())
            throw new BrandException(NAME_MANDATORY);
        String nameTrim = name.trim().toUpperCase();
        if(nameTrim.length() > MAXIMUM_ALLOW_LETTERS)
            throw new BrandException(NAME_MAX_ERROR);
        return nameTrim;
    }
}
