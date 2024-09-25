package com.example.stock.brand.brandapplicationtest;


import com.example.stock.application.brand.command.BrandCreateHandler;
import com.example.stock.brand.instance.BrandDtoMapperInstance;
import com.example.stock.brand.instance.Dao;
import com.example.stock.brand.instance.Repository;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import com.example.stock.domain.brand.service.BrandCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrandCreateHandlerTest {
    private BrandCreateHandler brandCreateHandler;
    @BeforeEach
    public void setUp() {
        // Create DAO and Repository with the sample Brand list
        List<Brand> initialBrands = Arrays.asList(
                new Brand(1L, "Brand1","d"),
                new Brand(2L, "Brand2","d")
        );
        // Create DAO and Repository with the sample Brand list
        BrandDao brandDao = new Dao(new ArrayList<>(initialBrands));
        BrandRepository brandRepository = new Repository(new ArrayList<>(initialBrands));

        // Create the service instance
        BrandCreateService brandCreateService = new BrandCreateService(brandRepository,brandDao);
        BrandDtoMapperInstance brandDtoMapper = new BrandDtoMapperInstance();
        brandCreateHandler = new BrandCreateHandler(brandCreateService,brandDtoMapper);
    }

    @Test
    void handler_createsBrand_successfully() {
        // arrange
        BrandCreateCommand command = new BrandCreateCommand("Brand3","dsfd");
        // act
        BrandDto createdBrand = brandCreateHandler.execute(command);
        // assert
        assertNotNull(createdBrand);
        assertEquals("BRAND3", createdBrand.getName());
    }

    @Test
    void handler_createBrand_whenEmptyName_shouldThrowsBrandException() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("", "description");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createBrand_whenNameTooLong_shouldThrowsBrandException() {
        // arrange
        String longName = "A".repeat(51); // 101 characters long
        BrandCreateCommand command = new BrandCreateCommand(longName, "description");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }

    @Test
    void handler_createBrand_whenEmptyDescription_shouldThrowsBrandException() {
        // arrange
        BrandCreateCommand command = new BrandCreateCommand("longName", "");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createBrand_whenDescriptionTooLong_shouldThrowsBrandException() {
        // arrange
        String longDescription = "A".repeat(121); // 101 characters long
        BrandCreateCommand command = new BrandCreateCommand("longName", longDescription);
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Description don't be bigger than 120 characters", exception.getErrorMessage());
    }
}
