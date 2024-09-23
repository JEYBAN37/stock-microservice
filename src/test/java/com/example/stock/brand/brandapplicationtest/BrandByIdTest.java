package com.example.stock.brand.brandapplicationtest;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.application.brand.query.BrandById;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

 class BrandByIdTest {
    @Mock
    private BrandDao brandDao;

    @Mock
    private BrandDtoMapper brandDtoMapper;

    @InjectMocks
    private BrandById brandById;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void execute_ShouldReturnBrandDto_WhenBrandExists() {
        // Arrange
        Long brandId = 1L;
        BrandDto expectedBrandDto = new BrandDto();
        Brand brand = new Brand();

        when(brandDao.getById(brandId)).thenReturn(brand);
        when(brandDtoMapper.toDto(brand)).thenReturn(expectedBrandDto);

        // Act
        BrandDto actualBrandDto = brandById.execute(brandId);

        // Assert
        assertThat(actualBrandDto).isEqualTo(expectedBrandDto);
    }

    @Test
     void execute_ShouldReturnNull_WhenBrandDoesNotExist() {
        // Arrange
        Long brandId = 2L;
        when(brandDao.getById(brandId)).thenReturn(null);

        // Act
        BrandDto actualBrandDto = brandById.execute(brandId);

        // Assert
        assertThat(actualBrandDto).isNull();
    }
}
