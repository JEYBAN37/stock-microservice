package com.example.stock.application.brand.command;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandEditCommand;
import com.example.stock.domain.brand.service.BrandUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrandUpdateHandler {
    private final BrandUpdateService brandUpdateService;
    private final BrandDtoMapper brandDtoMapper;

    public BrandDto execute (BrandEditCommand brandEditCommand, Long id){
        return brandDtoMapper.toDto(brandUpdateService.execute(brandEditCommand,id));
    }
}

