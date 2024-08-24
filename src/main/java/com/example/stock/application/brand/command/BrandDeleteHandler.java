package com.example.stock.application.brand.command;


import com.example.stock.domain.brand.service.BrandDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrandDeleteHandler {
    private final BrandDeleteService brandDeleteService;
    public void execute(Long categoryId) {
        brandDeleteService.execute(categoryId);
    }
}
