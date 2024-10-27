package com.example.stock.brand.brandapplicationtest;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.application.brand.query.BrandGetAll;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.domain.brand.service.BrandFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BrandGetAllHandlreTest {
   @Mock
   private BrandDao brandDao;

   @Mock
   private BrandDtoMapper brandDtoMapper;

   @InjectMocks
   private BrandGetAll brandGetAll;
    @Mock
    private BrandFilterService brandFilterService;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
   }




    @Test
    void execute_shouldReturnCategoriesInDescendingOrder() {
        // arrange
        Brand brandDto1 = new Brand( 1L,"Article A", "Description A");
        Brand brandDto2 = new Brand( 2L,"Article B", "Description B");
        List<Brand> expectedCategories = Arrays.asList(brandDto2, brandDto1);
        when(brandFilterService.execute(0, 10, false)).thenReturn(expectedCategories);

        BrandDto uno = new BrandDto( 1L,"Article A", "Description A");
        BrandDto dos = new BrandDto( 2L,"Article B", "Description B");
        List<BrandDto> expected = Arrays.asList(dos, uno);
        when(brandDtoMapper.toDto(brandDto1)).thenReturn(uno);
        when(brandDtoMapper.toDto(brandDto2)).thenReturn(dos);
        // act
        List<BrandDto> result = brandGetAll.execute(0, 10, false);
        // assert
        assertEquals(expected, result);
    }

    @Test
    void execute_shouldReturnCategoriesInAscendingOrder() {
        // arrange
        Brand brand1 = new Brand(1L, "Article A", "Description A");
        Brand brand2 = new Brand(2L, "Article B", "Description B");
        List<Brand> expectedBrands = Arrays.asList(brand1, brand2); // Lista en orden ascendente

        // Configuramos el mock del servicio para devolver la lista esperada en orden ascendente
        when(brandFilterService.execute(0, 10, true)).thenReturn(expectedBrands);

        // Configuramos el mock del mapper para que convierta correctamente los objetos de Brand a BrandDto
        BrandDto brandDto1 = new BrandDto(1L, "Article A", "Description A");
        BrandDto brandDto2 = new BrandDto(2L, "Article B", "Description B");
        List<BrandDto> expectedDtos = Arrays.asList(brandDto1, brandDto2); // Lista esperada de BrandDto en orden ascendente

        // Simulamos el mapeo de Brand a BrandDto
        when(brandDtoMapper.toDto(brand1)).thenReturn(brandDto1);
        when(brandDtoMapper.toDto(brand2)).thenReturn(brandDto2);

        // act
        List<BrandDto> result = brandGetAll.execute(0, 10, true);

        // assert
        assertEquals(expectedDtos, result);
    }


    @Test
    void execute_shouldReturnEmptyListWhenNoCategories() {
        // arrange
        when(brandFilterService.execute(0, 10, true)).thenReturn(Collections.emptyList());
        // act
        List<BrandDto> result = brandGetAll.execute(0, 10, true);
        // assert
        assertEquals(0, result.size());
    }
}
