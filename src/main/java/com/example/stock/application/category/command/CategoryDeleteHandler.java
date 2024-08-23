package com.example.stock.application.category.command;


import com.example.stock.domain.category.service.CategoryDeleteService;
import org.springframework.stereotype.Component;

@Component
public class CategoryDeleteHandler {
    private final CategoryDeleteService categoryDeleteService;

    public CategoryDeleteHandler(CategoryDeleteService categoryDeleteService) {
        this.categoryDeleteService = categoryDeleteService;
    }

    public void execute(Long categoryId) {
        categoryDeleteService.execute(categoryId);
    }
}
