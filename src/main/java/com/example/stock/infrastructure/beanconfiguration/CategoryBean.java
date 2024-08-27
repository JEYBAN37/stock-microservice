package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.application.category.mapper.CategoryDtoMapper;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryBean {
    @Bean
    public CategoryCreateService categoryCreateService(CategoryRepository categoryRepository , CategoryDao categoryDao){
        return new CategoryCreateService(categoryRepository,categoryDao);
    }

    @Bean
    public CategoryDeleteService categoryDeleteService(CategoryRepository categoryRepository, CategoryDao categoryDao){
        return new CategoryDeleteService(categoryRepository, categoryDao);
    }

    @Bean
    public CategoryUpdateService categoryUpdateService(CategoryRepository categoryRepository,
                                                       CategoryDao categoryDao){
        return new CategoryUpdateService(categoryRepository, categoryDao);
    }

    @Bean
    public CategoryFilterService categoryFilterService( CategoryDtoMapper categoryDtoMapper,
                                                       CategoryDao categoryDao){
        return new CategoryFilterService(categoryDtoMapper,categoryDao);
    }

    @Bean
    public CategoryListArticle categoryListArticle(CategoryDao categoryDao){
        return new CategoryListArticle(categoryDao);
    }


}
