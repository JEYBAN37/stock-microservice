package com.example.stock.brand.brandapplicationtest;

import com.example.stock.application.brand.mapper.BrandDtoMapper;
import com.example.stock.application.brand.query.BrandByName;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.dao.BrandDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BrandByNameTest {

    @Mock
    private BrandDao brandDao;

    @Mock
    private BrandDtoMapper brandDtoMapper;

    @InjectMocks
    private BrandByName brandByName;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute_ShouldReturnBrandDto_WhenBrandExists() {
        // Arrange
        String brandName = "Brand A";
        BrandDto expectedBrandDto = new BrandDto();
        Brand brand = new Brand();

        when(brandDao.getByName(brandName)).thenReturn(brand);
        when(brandDtoMapper.toDto(brand)).thenReturn(expectedBrandDto);

        // Act
        BrandDto actualBrandDto = brandByName.execute(brandName);

        // Assert
        assertThat(actualBrandDto).isEqualTo(expectedBrandDto);
    }

    @Test
    public void execute_ShouldReturnNull_WhenBrandDoesNotExist() {
        // Arrange
        String brandName = "Brand Unknown";

        when(brandDao.getByName(brandName)).thenReturn(null);

        // Act
        BrandDto actualBrandDto = brandByName.execute(brandName);

        // Assert
        assertThat(actualBrandDto).isNull();
    }
}
