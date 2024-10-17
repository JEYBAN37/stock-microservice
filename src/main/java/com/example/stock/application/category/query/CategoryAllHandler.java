package com.example.stock.application.category.query;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.service.CategoryFilterService;
import com.example.stock.domain.category.model.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.stock.domain.static_variables.StaticData.TEN_CONSTANT;
import static com.example.stock.domain.static_variables.StaticData.ZERO_CONSTANT;

@AllArgsConstructor
@Component
public class CategoryAllHandler {
    private  final CategoryFilterService categoryFilterService;
    private  final CategoryDtoMapper categoryDtoMapper;
    public List<CategoryDto> execute(Integer page, Integer size, Boolean ascending) {

        int pageNumber = (page == null) ? ZERO_CONSTANT : page;
        int pageSize = (size == null) ? TEN_CONSTANT : size;
        boolean isAscending = (ascending != null) && ascending;
        return categoryFilterService.execute(pageNumber,pageSize,isAscending)
                .stream()
                .map(categoryDtoMapper::toDto)
                .toList();
    }

}
