package com.example.stock.config.globalexception;


import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.category.model.exception.CategoryException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MESSAGE = "message";
    private static final String TIMESTAMP = "timestamp";
    private static final String DETAILS = "details";
    @ExceptionHandler(BrandException.class)
    public ResponseEntity<Object> brandHandleBrandException(BrandException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getErrorMessage());
        body.put(DETAILS, request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<Object> articleHandleBrandException(ArticleException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getErrorMessage());
        body.put(DETAILS, request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<Object> categoryHandleBrandException(CategoryException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getErrorMessage());
        body.put(DETAILS, request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, "Token expired: " + ex.getMessage());
        body.put(DETAILS, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
