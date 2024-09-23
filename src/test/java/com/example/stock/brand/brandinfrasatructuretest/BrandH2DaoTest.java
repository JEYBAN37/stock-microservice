package com.example.stock.brand.brandinfrasatructuretest;

import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import com.example.stock.infrastructure.brand.adapter.jpa.BrandSpringJpaAdapterRepository;
import com.example.stock.infrastructure.brand.adapter.jpa.dao.BrandH2Dao;
import com.example.stock.infrastructure.brand.adapter.mapper.BrandDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandH2DaoTest {

    @Mock
    private BrandSpringJpaAdapterRepository brandSpringJpaAdapterRepository;

    @Mock
    private BrandDboMapper brandDboMapper;

    @InjectMocks
    private BrandH2Dao brandH2Dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void getByName_ShouldReturnBrandWhenFound() {
        // Arrange
        String name = "Sample Brand";
        BrandEntity brandEntity = new BrandEntity(); // Populate with necessary fields
        Brand expectedBrand = new Brand(); // Populate with necessary fields

        when(brandSpringJpaAdapterRepository.findByName(name)).thenReturn(brandEntity);
        when(brandDboMapper.toDomain(brandEntity)).thenReturn(expectedBrand);

        // Act
        Brand actualBrand = brandH2Dao.getByName(name);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(brandSpringJpaAdapterRepository, times(1)).findByName(name);
        verify(brandDboMapper, times(1)).toDomain(brandEntity);
    }

    @Test
     void getByName_ShouldReturnNullWhenNotFound() {
        // Arrange
        String name = "Non-existent Brand";
        when(brandSpringJpaAdapterRepository.findByName(name)).thenReturn(null);

        // Act
        Brand actualBrand = brandH2Dao.getByName(name);

        // Assert
        assertNull(actualBrand);
        verify(brandSpringJpaAdapterRepository, times(1)).findByName(name);
    }

    @Test
     void getById_ShouldReturnBrandWhenFound() {
        // Arrange
        Long id = 1L;
        BrandEntity brandEntity = new BrandEntity(); // Populate with necessary fields
        Brand expectedBrand = new Brand(); // Populate with necessary fields

        when(brandSpringJpaAdapterRepository.findById(id)).thenReturn(Optional.of(brandEntity));
        when(brandDboMapper.toDomain(brandEntity)).thenReturn(expectedBrand);

        // Act
        Brand actualBrand = brandH2Dao.getById(id);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(brandSpringJpaAdapterRepository, times(1)).findById(id);
        verify(brandDboMapper, times(1)).toDomain(brandEntity);
    }

    @Test
     void getById_ShouldReturnNullWhenNotFound() {
        // Arrange
        Long id = 1L;
        when(brandSpringJpaAdapterRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Brand actualBrand = brandH2Dao.getById(id);

        // Assert
        assertNull(actualBrand);
        verify(brandSpringJpaAdapterRepository, times(1)).findById(id);
    }

    @Test
     void nameExist_ShouldReturnTrueWhenNameExists() {
        // Arrange
        String name = "Existing Brand";
        when(brandSpringJpaAdapterRepository.existsByName(name)).thenReturn(true);

        // Act
        boolean exists = brandH2Dao.nameExist(name);

        // Assert
        assertTrue(exists);
        verify(brandSpringJpaAdapterRepository, times(1)).existsByName(name);
    }

    @Test
     void idExist_ShouldReturnTrueWhenIdExists() {
        // Arrange
        Long id = 1L;
        when(brandSpringJpaAdapterRepository.existsById(id)).thenReturn(true);

        // Act
        boolean exists = brandH2Dao.idExist(id);

        // Assert
        assertTrue(exists);
        verify(brandSpringJpaAdapterRepository, times(1)).existsById(id);
    }

    @Test
     void getAll_ShouldReturnListOfBrands() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascending = true;

        BrandEntity brandEntity1 = new BrandEntity(); // Populate with necessary fields
        BrandEntity brandEntity2 = new BrandEntity(); // Populate with necessary fields
        Brand expectedBrand1 = new Brand(); // Populate with necessary fields
        Brand expectedBrand2 = new Brand(); // Populate with necessary fields

        when(brandSpringJpaAdapterRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(brandEntity1, brandEntity2)));

        when(brandDboMapper.toDomain(brandEntity1)).thenReturn(expectedBrand1);
        when(brandDboMapper.toDomain(brandEntity2)).thenReturn(expectedBrand2);

        // Act
        List<Brand> actualBrands = brandH2Dao.getAll(page, size, ascending);

        // Assert
        assertEquals(2, actualBrands.size());
        assertEquals(expectedBrand1, actualBrands.get(0));
        assertEquals(expectedBrand2, actualBrands.get(1));
        verify(brandSpringJpaAdapterRepository, times(1)).findAll(any(Pageable.class));
    }
}
