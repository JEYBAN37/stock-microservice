package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.CategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class CategoryDescription {
    private static final int MAXIMUM_ALLOW_LETTERS = 90;
    String description;

    public CategoryDescription(String description){
        toValidDescription(description);
        this.description = description;
    }
    private void toValidDescription(String name){
        if(name.isEmpty())
            throw new CategoryException("Description is mandatory");
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new CategoryException("Description don't be bigger than 90 characters");
    }
}
