package com.example.stock.brand.brandapplicationtest;


import com.example.stock.application.brand.command.BrandCreateHandler;
import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.application.category.command.CategoryCreateHandler;
import com.example.stock.brand.instance.BrandDtoMapperInstance;
import com.example.stock.brand.instance.Dao;
import com.example.stock.brand.instance.Repository;
import com.example.stock.category.instance.CategoryDtoMapperInstance;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import com.example.stock.domain.brand.service.BrandCreateService;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.domain.category.service.CategoryCreateService;
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
        // Create DAO and Repository with the sample category list
        List<Brand> initialBrands = Arrays.asList(
                new Brand(1L, "Category1","d"),
                new Brand(2L, "Category2","d")
        );
        // Create DAO and Repository with the sample category list
        BrandDao brandDao = new Dao(new ArrayList<>(initialBrands));
        BrandRepository brandRepository = new Repository(new ArrayList<>(initialBrands));

        // Create the service instance
        BrandCreateService brandCreateService = new BrandCreateService(brandRepository,brandDao);
        BrandDtoMapperInstance brandDtoMapper = new BrandDtoMapperInstance();
        brandCreateHandler = new BrandCreateHandler(brandCreateService,brandDtoMapper);
    }

    @Test
    void handler_createsCategory_successfully() {
        // arrange
        BrandCreateCommand command = new BrandCreateCommand("Category3","dsfd");
        // act
        BrandDto createdBrand = brandCreateHandler.execute(command);
        // assert
        assertNotNull(createdBrand);
        assertEquals("Category3", createdBrand.getName());
        assertEquals(3L, createdBrand.getId()); // Assuming auto-increment logic
    }
    @Test
    void handler_createsCategory_whenExistCategory_shouldThrowsCategoryException() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("Category2","dsfd");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Brand Exist", exception.getErrorMessage());

    }

    @Test
    void handler_createCategory_whenEmptyName_shouldThrowsCategoryException() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("", "description");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createCategory_whenNameTooLong_shouldThrowsCategoryException() {
        // arrange
        String longName = "A".repeat(51); // 101 characters long
        BrandCreateCommand command = new BrandCreateCommand(longName, "description");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }

    @Test
    void handler_createCategory_whenEmptyDescription_shouldThrowsCategoryException() {
        // arrange
        BrandCreateCommand command = new BrandCreateCommand("longName", "");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }

    @Test
    void handler_createCategory_whenDescriptionTooLong_shouldThrowsCategoryException() {
        // arrange
        String longDescription = "A".repeat(121); // 101 characters long
        BrandCreateCommand command = new BrandCreateCommand("longName", longDescription);
        // act
        BrandException exception = assertThrows(BrandException.class, () -> brandCreateHandler.execute(command));
        // assert
        assertEquals("Description don't be bigger than 120 characters", exception.getErrorMessage());
    }



}
