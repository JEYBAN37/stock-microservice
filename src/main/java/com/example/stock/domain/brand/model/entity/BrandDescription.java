package com.example.stock.domain.brand.model.entity;

import com.example.stock.domain.brand.model.exception.BrandException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;


@NoArgsConstructor
@Getter
public class BrandDescription {
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
        if(name.length() > MAXIMUM_ALLOW_LETTERS_VAR)
            throw new BrandException(DESCRIPTION_MESSAGE_MAX_ERROR_VAR);
    }
}
