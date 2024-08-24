package com.example.stock.brand.branddoamintest;

import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.domain.brand.service.BrandFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BrandFilterServiceTest {

    @Mock
    private BrandDao brandDao;

    @Mock
    private BrandDtoMapper brandDtoMapper;

    @InjectMocks
    private BrandFilterService brandFilterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldReturnMappedBrandDtos() {
        // arrange
        Brand brand1 = new Brand(1L, "Brand A", "Description A");
        Brand brand2 = new Brand(2L, "Brand B", "Description B");
        BrandDto brandDto1 = new BrandDto(1L, "Brand A", "Description A");
        BrandDto brandDto2 = new BrandDto(2L, "Brand B", "Description B");
        List<Brand> expectedDtos = List.of(brand1, brand2);
        when(brandDao.getAll(0, 10, true)).thenReturn(List.of(brand1, brand2));
        when(brandDtoMapper.toDto(brand1)).thenReturn(brandDto1);
        when(brandDtoMapper.toDto(brand2)).thenReturn(brandDto2);
        // act
        List<BrandDto> result = brandFilterService.execute(0, 10, true);
        // assert
        assertEquals(expectedDtos.size(), result.size());
        assertEquals(expectedDtos.get(0).getName(), result.get(0).getName());
        assertEquals(expectedDtos.get(1).getName(), result.get(1).getName());
    }
    @Test
    void execute_shouldReturnEmptyList_whenNoBrandsAvailable() {
        // arrange
        when(brandDao.getAll(0, 10, true)).thenReturn(List.of());
        // act
        List<BrandDto> result = brandFilterService.execute(0, 10, true);
        // assert
        assertEquals(0, result.size());
    }
    @Test
    void execute_shouldReturnBrandsInAscendingOrder() {
        // arrange
        Brand brand1 = new Brand(1L, "Brand A", "Description A");
        Brand brand2 = new Brand(2L, "Brand B", "Description B");
        BrandDto brandDto1 = new BrandDto(1L, "Brand A", "Description A");
        BrandDto brandDto2 = new BrandDto(2L, "Brand B", "Description B");
        when(brandDao.getAll(0, 10, true)).thenReturn(List.of(brand1, brand2));
        when(brandDtoMapper.toDto(brand1)).thenReturn(brandDto1);
        when(brandDtoMapper.toDto(brand2)).thenReturn(brandDto2);
        // act
        List<BrandDto> result = brandFilterService.execute(0, 10, true);
        // assert
        assertEquals("Brand A", result.get(0).getName());
        assertEquals("Brand B", result.get(1).getName());
    }

    @Test
    void execute_shouldReturnBrandsInDescendingOrder() {
        // arrange
        Brand brand1 = new Brand(1L, "Brand A", "Description A");
        Brand brand2 = new Brand(2L, "Brand B", "Description B");
        BrandDto brandDto1 = new BrandDto(1L, "Brand A", "Description A");
        BrandDto brandDto2 = new BrandDto(2L, "Brand B", "Description B");
        when(brandDao.getAll(0, 10, false)).thenReturn(List.of(brand2, brand1));
        when(brandDtoMapper.toDto(brand1)).thenReturn(brandDto1);
        when(brandDtoMapper.toDto(brand2)).thenReturn(brandDto2);
        // act
        List<BrandDto> result = brandFilterService.execute(0, 10, false);
        // assert
        assertEquals("Brand B", result.get(0).getName());
        assertEquals("Brand A", result.get(1).getName());
    }
    @Test
    void execute_shouldCallMapperForEachBrand() {
        // arrange
        Brand brand1 = new Brand(1L, "Brand A", "Description A");
        Brand brand2 = new Brand(2L, "Brand B", "Description B");
        BrandDto brandDto1 = new BrandDto(1L, "Brand A", "Description A");
        BrandDto brandDto2 = new BrandDto(2L, "Brand B", "Description B");
        when(brandDao.getAll(0, 10, true)).thenReturn(List.of(brand1, brand2));
        when(brandDtoMapper.toDto(brand1)).thenReturn(brandDto1);
        when(brandDtoMapper.toDto(brand2)).thenReturn(brandDto2);
        // act
        List<BrandDto> result = brandFilterService.execute(0, 10, true);
        // assert
        assertEquals(2, result.size());
        assertEquals("Brand A", result.get(0).getName());
        assertEquals("Brand B", result.get(1).getName());
    }
    @Test
    void execute_shouldHandleDifferentPageSizes() {
        // arrange
        Brand brand1 = new Brand(1L, "Brand A", "Description A");
        Brand brand2 = new Brand(2L, "Brand B", "Description B");
        BrandDto brandDto1 = new BrandDto(1L, "Brand A", "Description A");
        BrandDto brandDto2 = new BrandDto(2L, "Brand B", "Description B");
        when(brandDao.getAll(0, 1, true)).thenReturn(List.of(brand1));
        when(brandDtoMapper.toDto(brand1)).thenReturn(brandDto1);
        // act
        List<BrandDto> result = brandFilterService.execute(0, 1, true);
        // assert
        assertEquals(1, result.size());
        assertEquals("Brand A", result.get(0).getName());
    }


}
