package com.example.stock.brand.brandinfrasatructuretest;

import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import com.example.stock.infrastructure.brand.adapter.jpa.BrandSpringJpaAdapterRepository;
import com.example.stock.infrastructure.brand.adapter.jpa.respository.BrandH2Repository;
import com.example.stock.infrastructure.brand.adapter.mapper.BrandDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandH2RepositoryTest {

    @Mock
    private BrandSpringJpaAdapterRepository brandSpringJpaAdapterRepository;

    @Mock
    private BrandDboMapper brandDboMapper;

    @InjectMocks
    private BrandH2Repository brandH2Repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void create_ShouldReturnBrandWhenSuccessful() {
        // Arrange
        Brand request = new Brand();
        BrandEntity brandEntity = new BrandEntity();
        BrandEntity savedEntity = new BrandEntity();
        Brand expectedBrand = new Brand();

        when(brandDboMapper.toDatabase(request)).thenReturn(brandEntity);
        when(brandSpringJpaAdapterRepository.save(brandEntity)).thenReturn(savedEntity);
        when(brandDboMapper.toDomain(savedEntity)).thenReturn(expectedBrand);

        // Act
        Brand actualBrand = brandH2Repository.create(request);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(brandDboMapper, times(1)).toDatabase(request);
        verify(brandSpringJpaAdapterRepository, times(1)).save(brandEntity);
        verify(brandDboMapper, times(1)).toDomain(savedEntity);
    }

    @Test
     void deleteById_ShouldInvokeDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        brandH2Repository.deleteById(id);

        // Assert
        verify(brandSpringJpaAdapterRepository, times(1)).deleteById(id);
    }

    @Test
     void update_ShouldReturnUpdatedBrandWhenSuccessful() {
        // Arrange
        Brand request = new Brand();
        BrandEntity brandEntity = new BrandEntity();
        BrandEntity updatedEntity = new BrandEntity();
        Brand expectedBrand = new Brand();

        when(brandDboMapper.toDatabase(request)).thenReturn(brandEntity);
        when(brandSpringJpaAdapterRepository.save(brandEntity)).thenReturn(updatedEntity);
        when(brandDboMapper.toDomain(updatedEntity)).thenReturn(expectedBrand);

        // Act
        Brand actualBrand = brandH2Repository.update(request);

        // Assert
        assertEquals(expectedBrand, actualBrand);
        verify(brandDboMapper, times(1)).toDatabase(request);
        verify(brandSpringJpaAdapterRepository, times(1)).save(brandEntity);
        verify(brandDboMapper, times(1)).toDomain(updatedEntity);
    }
}
