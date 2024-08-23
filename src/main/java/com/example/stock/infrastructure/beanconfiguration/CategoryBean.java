package com.example.stock.infrastructure.beanconfiguration;

import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.CategoryCreateService;
import com.example.stock.domain.category.service.CategoryDeleteService;
import com.example.stock.domain.category.service.CategoryUpdateService;
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

}
