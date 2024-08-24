package com.example.stock.domain.brand.service;


import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrandCreateService {
    private final BrandRepository brandRepository;
    private final BrandDao brandDao;

    private static final String MESSAGE_ERROR_ADD = "Brand Exist";
    public Brand execute (BrandCreateCommand brandCreateCommand){
        if (brandDao.nameExist(brandCreateCommand.getName()))
            throw new BrandException(MESSAGE_ERROR_ADD);
        Brand BrandToCreate = new Brand().requestToCreate(brandCreateCommand);
        return brandRepository.create(BrandToCreate);
    }
}
