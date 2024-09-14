package com.example.stock.domain.category.model.entity;

import com.example.stock.domain.category.model.exception.CategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;


@NoArgsConstructor
@Getter
public class CategoryName {
    String name;

    public CategoryName(String name) {
        toValidName(name);
        this.name = name;
    }

    private void toValidName(String name){
        if(name == null || name.isEmpty())
            throw new CategoryException(NAME_MANDATORY);
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new CategoryException(NAME_MAX_ERROR);
    }
}
