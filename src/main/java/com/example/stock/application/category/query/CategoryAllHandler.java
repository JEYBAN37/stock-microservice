package com.example.stock.application.category.query;

import com.example.stock.domain.category.service.CategoryFilterService;
import com.example.stock.domain.category.model.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CategoryAllHandler {
    private  final CategoryFilterService categoryFilterService;
    public List<CategoryDto> execute(int page, int size, boolean ascending) {
        return categoryFilterService.execute(page,size,ascending);
    }

}
