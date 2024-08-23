package com.example.stock.domain.brand.service;

import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BrandDeleteService {
    private final BrandRepository categoryRepository;
    private final BrandDao categoryDao;
    private static final String MESSAGE_ERROR_REMOVE = "Error to Remove No Exist";

    public void execute(Long id){
        if (!categoryDao.idExist(id))
            throw new BrandException(MESSAGE_ERROR_REMOVE);
        categoryRepository.deleteById(id);
    }
}
