package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BrandDescription {
    private static final int MAXIMUM_ALLOW_LETTERS = 90;
    private static final String DESCRIPTION_MESSAGE = "Description is mandatory";
    private static final String DESCRIPTION_MESSAGE_MAX_VALUES = "Description don't be bigger than 120 characters";
    String description;

    public BrandDescription(String description){
        this.description = description;
    }

    public static BrandDescription of(String description) {
        toValidDescription(description);
        return new BrandDescription(description);
    }
    private static void toValidDescription(String name){
        if(name.isEmpty())
            throw new BrandException(DESCRIPTION_MESSAGE);
        if(name.length() > MAXIMUM_ALLOW_LETTERS)
            throw new BrandException(DESCRIPTION_MESSAGE_MAX_VALUES);
    }
}
