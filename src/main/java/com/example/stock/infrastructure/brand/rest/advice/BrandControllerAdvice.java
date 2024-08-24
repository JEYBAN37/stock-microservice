package com.example.stock.infrastructure.brand.rest.advice;



import com.example.stock.domain.brand.model.exception.BrandException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BrandControllerAdvice {
    @ExceptionHandler(BrandException.class)
    public ResponseEntity<String> handleEmptyInput(BrandException emptyInputException){
        return new ResponseEntity<>(emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }
}
