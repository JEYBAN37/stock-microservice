package com.example.stock.domain.category.model.entity;

import com.example.stock.domain.category.model.exception.CategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;


@NoArgsConstructor
@Getter
public class CategoryName {
    String name;

    private CategoryName(String name) {
        this.name = name;
    }

    public static CategoryName of (String name){
        return new CategoryName(toValidName(name));
    }

    private static String toValidName(String name){
        if( name == null || name.isEmpty())
            throw new CategoryException(NAME_MANDATORY);
        String nameTrip = name.trim().toUpperCase();
        if(nameTrip.length() > MAXIMUM_ALLOW_LETTERS)
            throw new CategoryException(NAME_MAX_ERROR);
        return nameTrip;
    }
}
