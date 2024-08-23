package com.example.stock.brand.branddoamintest;




import com.example.stock.brand.branddoamintest.instance.Dao;
import com.example.stock.brand.branddoamintest.instance.Repository;
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
class BrandCreateServiceTest {
    private BrandCreateService brandCreateService;

    @BeforeEach
    public void setUp() {
        List<Brand> initialCategories = Arrays.asList(
                new Brand(1L, "Brand1","d"),
                new Brand(2L, "Brand2","d")
        );

        // Create DAO and Repository with the sample Brand list
        BrandDao BrandDao = new Dao(new ArrayList<>(initialCategories));
        BrandRepository BrandRepository = new Repository(new ArrayList<>(initialCategories));

        // Create the service instance
        brandCreateService = new BrandCreateService(BrandRepository, BrandDao);
    }
    @Test
     void createsBrand_successfully() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("Brand3","dsfd");
        // act
        Brand createdBrand = brandCreateService.execute(command);
        // assert
        assertNotNull(createdBrand);
        assertEquals("Brand3", createdBrand.getName());
        assertEquals(3L, createdBrand.getId()); // Assuming auto-increment logic

    }
    @Test
    void createBrand_whenExistBrand_shouldThrowsBrandException() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("Brand2","dsfd");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> {
            brandCreateService.execute(command);
        });
        // assert
        assertEquals("Brand Exist", exception.getErrorMessage());

    }
    @Test
    void createBrand_whenEmptyName_shouldThrowsBrandException() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("", "description");
        //act
        BrandException exception = assertThrows(BrandException.class, () -> {
            brandCreateService.execute(command);
        });
        //assert
        assertEquals("Name is mandatory", exception.getErrorMessage());
    }
    @Test
    void createBrand_whenNameTooLong_shouldThrowsBrandException() {
        // arrange
        String longName = "A".repeat(51);
        BrandCreateCommand command = new BrandCreateCommand(longName, "description");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> {
            brandCreateService.execute(command);
        });
        //assert
        assertEquals("Name don't be bigger than 50 characters", exception.getErrorMessage());
    }
    @Test
    void createBrand_whenEmptyDescription_shouldThrowsBrandException() {
        //arrange
        BrandCreateCommand command = new BrandCreateCommand("longName", "");
        // act
        BrandException exception = assertThrows(BrandException.class, () -> {
            brandCreateService.execute(command);
        });
        //assert
        assertEquals("Description is mandatory", exception.getErrorMessage());
    }
    @Test
    void createBrand_whenDescriptionTooLong_shouldThrowsBrandException() {
        //Arrange
        String longDescription = "A".repeat(91);
        BrandCreateCommand command = new BrandCreateCommand("longName", longDescription);

      //Act
        BrandException exception = assertThrows(BrandException.class, () -> {
            brandCreateService.execute(command);
        });
        //Assert
        assertEquals("Description don't be bigger than 120 characters", exception.getErrorMessage());
    }

}
