package com.example.stock.brand.brandinfrasatructuretest;

import com.example.stock.application.brand.query.BrandAllHandler;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.infrastructure.brand.rest.controller.BrandQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ListBrandsTest {
    @Mock
    private BrandAllHandler brandAllHandler;

    @InjectMocks
    private BrandQueryController brandQueryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAll_shouldReturnBrands() {
        // arrange
        BrandDto brandDto1 = new BrandDto( 1L,"Article A", "Description A");
        BrandDto brandDto2 = new BrandDto( 1L,"Article B", "Description B");
        List<BrandDto> expectedBrands = List.of(brandDto1, brandDto2);
        when(brandAllHandler.execute(0, 10, false)).thenReturn(expectedBrands);
        // act
        List<BrandDto> result = brandQueryController.getAll(0, 10, false);
        // assert
        assertEquals(expectedBrands, result);
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNoBrands() {
        // arrange
        when(brandAllHandler.execute(0, 10, false)).thenReturn(List.of());
        // act
        List<BrandDto> result = brandQueryController.getAll(0, 10, true);
        // assert
        assertEquals(0, result.size());
    }

    @Test
    void getAll_shouldReturnBrandsInAscendingOrder() {
        // arrange
        BrandDto brandDto1 = new BrandDto( 1L,"Article A", "Description A");
        BrandDto brandDto2 = new BrandDto( 1L,"Article B", "Description B");
        List<BrandDto> expectedBrands = List.of(brandDto1, brandDto2);
        when(brandAllHandler.execute(0, 10, true)).thenReturn(expectedBrands);
        // Act
        List<BrandDto> result = brandQueryController.getAll(0, 10, true);
        // Assert
        assertEquals(expectedBrands, result);
        assertEquals(expectedBrands.get(0).getName(), result.get(0).getName());
        assertEquals(expectedBrands.get(1).getName(), result.get(1).getName());
    }

    @Test
    void getAll_shouldReturnBrandsInDescendingOrder() {
        // arrange
        BrandDto brandDto1 = new BrandDto( 1L,"Article A", "Description A");
        BrandDto brandDto2 = new BrandDto( 1L,"Article B", "Description B");
        List<BrandDto> expectedBrands = List.of(brandDto1, brandDto2);
        when(brandAllHandler.execute(0, 10, false)).thenReturn(expectedBrands);
        // Act
        List<BrandDto> result = brandQueryController.getAll(0, 10, false);
        // Assert
        assertEquals(expectedBrands, result);
        assertEquals(expectedBrands.get(0).getName(), result.get(0).getName());
        assertEquals(expectedBrands.get(1).getName(), result.get(1).getName());
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNoBrandsExist() {
        // arrange
        List<BrandDto> expectedBrands = List.of();
        when(brandAllHandler.execute(0, 10, true)).thenReturn(expectedBrands);
        // Act
        List<BrandDto> result = brandQueryController.getAll(0, 10, true);
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_shouldHandleNoDataFound() {
        // arrange
        when(brandAllHandler.execute(0, 10, true)).thenReturn(List.of());
        List<BrandDto> result = brandQueryController.getAll(0, 10, true);
        // Assert
        assertTrue(result.isEmpty());
    }

}
