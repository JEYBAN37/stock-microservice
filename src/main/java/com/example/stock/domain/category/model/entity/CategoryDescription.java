package com.example.stock.domain.category.model.entity;

import com.example.stock.domain.category.model.exception.CategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.*;


@NoArgsConstructor
@Getter
public class CategoryDescription {
    String description;

    public CategoryDescription(String description){
        toValidDescription(description);
        this.description = description;
    }
    private void toValidDescription(String name){
        if(name == null || name.isEmpty())
            throw new CategoryException(DESCRIPTION_MESSAGE);
        if(name.length() > MAXIMUM_ALLOW_LETTERS_DESCRIPTION)
            throw new CategoryException(DESCRIPTION_MESSAGE_MAX_ERROR);
    }
}
