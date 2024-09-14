package com.example.stock.domain.brand.service;


import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import lombok.AllArgsConstructor;

import static com.example.stock.domain.static_variables.StaticData.MESSAGE_ERROR_ADD_BRAND;

@AllArgsConstructor
public class BrandCreateService {
    private final BrandRepository brandRepository;
    private final BrandDao brandDao;


    public Brand execute (BrandCreateCommand brandCreateCommand){
        if (brandDao.nameExist(brandCreateCommand.getName()))
            throw new BrandException(MESSAGE_ERROR_ADD_BRAND);
        Brand brandToCreate = new Brand().requestToCreate(brandCreateCommand);
        return brandRepository.create(brandToCreate);
    }
}
