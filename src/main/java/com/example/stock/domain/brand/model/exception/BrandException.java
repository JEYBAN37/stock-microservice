package com.example.stock.domain.brand.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor(force = true)
@Getter
@Setter
public class BrandException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorMessage;
    public BrandException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
