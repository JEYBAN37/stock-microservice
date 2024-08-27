package com.example.stock.brand.brandapplicationtest;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.application.brand.query.BrandAllHandler;
import com.example.stock.domain.brand.model.dto.BrandDto;
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
   private BrandAllHandler brandAllHandler;
    @Mock
    private BrandFilterService brandFilterService;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
   }

    @Test
    void execute_shouldReturnCategoriesInAscendingOrder() {
        // arrange
        BrandDto brandDto1 = new BrandDto( 1L,"Article A", "Description A");
        BrandDto brandDto2 = new BrandDto( 1L,"Article B", "Description B");
        List<BrandDto> expectedCategories = Arrays.asList(brandDto1, brandDto2);
        when(brandFilterService.execute(0, 10, true)).thenReturn(expectedCategories);
        // act
        List<BrandDto> result = brandAllHandler.execute(0, 10, true);
        // assert
        assertEquals(expectedCategories, result);
    }


    @Test
    void execute_shouldReturnCategoriesInDescendingOrder() {
        // arrange
        BrandDto brandDto1 = new BrandDto( 1L,"Article A", "Description A");
        BrandDto brandDto2 = new BrandDto( 1L,"Article B", "Description B");
        List<BrandDto> expectedCategories = Arrays.asList(brandDto2, brandDto1);
        when(brandFilterService.execute(0, 10, false)).thenReturn(expectedCategories);
        // act
        List<BrandDto> result = brandAllHandler.execute(0, 10, false);
        // assert
        assertEquals(expectedCategories, result);
    }

    @Test
    void execute_shouldReturnEmptyListWhenNoCategories() {
        // arrange
        when(brandFilterService.execute(0, 10, true)).thenReturn(Collections.emptyList());
        // act
        List<BrandDto> result = brandAllHandler.execute(0, 10, true);
        // assert
        assertEquals(0, result.size());
    }
}
