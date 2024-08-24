package com.example.stock.infrastructure.brand.rest.advice;



import com.example.stock.domain.category.model.exception.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryControllerAdvice {
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<String> handleEmptyInput(CategoryException emptyInputException){
        return new ResponseEntity<>(emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }
}
