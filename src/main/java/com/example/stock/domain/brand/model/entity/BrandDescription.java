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
        return new BrandDescription( toValidDescription(description));
    }

    private static String toValidDescription(String description){
        if(description== null || description.isEmpty())
            throw new BrandException(DESCRIPTION_MESSAGE);
        String descripcionTrim = description.trim().toUpperCase();
        if(descripcionTrim.length() > MAXIMUM_ALLOW_LETTERS_VAR)
            throw new BrandException(DESCRIPTION_MESSAGE_MAX_ERROR_VAR);
        return descripcionTrim;
    }
}
