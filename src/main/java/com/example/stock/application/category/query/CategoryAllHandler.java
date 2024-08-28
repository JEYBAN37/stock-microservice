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
    public List<CategoryDto> execute(Integer page, Integer size, Boolean ascending) {

        int pageNumber = (page == null) ? 0 : page;
        int pageSize = (size == null) ? 10 : size;
        boolean isAscending = (ascending != null) && ascending;

        return categoryFilterService.execute(pageNumber,pageSize,isAscending);
    }

}
