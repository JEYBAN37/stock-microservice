package com.example.stock.brand.brandinfrasatructuretest;

import com.example.stock.application.brand.query.BrandGetAll;
import com.example.stock.application.brand.query.BrandById;
import com.example.stock.application.brand.query.BrandByName;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.infrastructure.brand.rest.controller.BrandQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class BrandQueryControllerTest {

    @Mock
    private BrandByName brandByName;

    @Mock
    private BrandById brandById;

    @Mock
    private BrandGetAll brandGetAll;

    @InjectMocks
    private BrandQueryController brandQueryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void getByName_ShouldReturnBrandDtoWhenFound() {
        // Arrange
        String name = "BrandName";
        BrandDto expectedBrand = new BrandDto(); // Populate with necessary fields

        when(brandByName.execute(name)).thenReturn(expectedBrand);

        // Act
        BrandDto actualBrand = brandQueryController.getByName(name);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(brandByName, times(1)).execute(name);
    }

    @Test
     void getById_ShouldReturnBrandDtoWhenFound() {
        // Arrange
        Long id = 1L;
        BrandDto expectedBrand = new BrandDto(); // Populate with necessary fields

        when(brandById.execute(id)).thenReturn(expectedBrand);

        // Act
        BrandDto actualBrand = brandQueryController.getById(id);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(brandById, times(1)).execute(id);
    }

    @Test
     void getAll_ShouldReturnListOfBrandDto() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;
        List<BrandDto> expectedBrands = new ArrayList<>(); // Populate with necessary fields

        when(brandGetAll.execute(page, size, ascending)).thenReturn(expectedBrands);

        // Act
        List<BrandDto> actualBrands = brandQueryController.getAll(page, size, ascending);

        // Assert
        assertEquals(expectedBrands, actualBrands);
        verify(brandGetAll, times(1)).execute(page, size, ascending);
    }
}
