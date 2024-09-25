package com.example.stock.domain.category.model.entity;

import com.example.stock.domain.category.model.exception.CategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;


@NoArgsConstructor
@Getter
public class CategoryDescription {
    String description;

    private CategoryDescription (String description){
        this.description = description;
    }

    public static CategoryDescription of (String description){
        return new CategoryDescription(toValidDescription(description));
    }
    private static String toValidDescription(String description){

        if(description == null || description.isEmpty())
            throw new CategoryException(DESCRIPTION_MESSAGE);

        var descriptionTrip =  description.trim().toUpperCase();

        if(descriptionTrip.length() > MAXIMUM_ALLOW_LETTERS_DESCRIPTION)
            throw new CategoryException(DESCRIPTION_MESSAGE_MAX_ERROR);
        return descriptionTrip;
    }
}
