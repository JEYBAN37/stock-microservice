package com.example.stock.brand.brandinfrasatructuretest;

import com.example.stock.application.brand.command.BrandCreateHandler;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.infrastructure.brand.rest.controller.BrandCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateBrandTest {
    @Mock
    private BrandCreateHandler brandCreateHandler;

    @InjectMocks
    private BrandCommandController brandCommandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBrand_successful() {
        // arrange
        BrandDto mockBrandDto = new BrandDto();
        mockBrandDto.setId(1L);
        mockBrandDto.setName("Test Brand");
        mockBrandDto.setDescription("This is a test Brand");
        BrandCreateCommand mockCreateCommand = new BrandCreateCommand();
        mockCreateCommand.setName("Test Brand");
        mockCreateCommand.setDescription("This is a test Brand");
        when(brandCreateHandler.execute(any(BrandCreateCommand.class))).thenReturn(mockBrandDto);
        // act
        BrandDto result = brandCommandController.create(mockCreateCommand);
        // assert
        assertEquals(mockBrandDto.getId(), result.getId());
        assertEquals(mockBrandDto.getName(), result.getName());
        assertEquals(mockBrandDto.getDescription(), result.getDescription());

    }
    @Test
    void createBrand_whenNameExist_shouldThrowsBrandException() {
        // arrange
        BrandCreateCommand mockCreateCommand = new BrandCreateCommand();
        mockCreateCommand.setName("Existing Brand Name");
        mockCreateCommand.setDescription("This is a test Brand");
        when(brandCreateHandler.execute(any(BrandCreateCommand.class)))
                .thenThrow(new BrandException("Brand with this name already exists"));
        // act & Assert
        assertThrows(BrandException.class, () -> {
            brandCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createBrand_whenNameExceedsMaxLength_shouldThrowBrandException() {
        // arrange
        BrandCreateCommand mockCreateCommand = new BrandCreateCommand();
        mockCreateCommand.setName( "A".repeat(51));
        mockCreateCommand.setDescription("Valid description");
        when(brandCreateHandler.execute(any(BrandCreateCommand.class)))
                .thenThrow(new BrandException("Name exceeds maximum length of 50 characters"));
        // act & Assert
        assertThrows(BrandException.class, () -> {
            brandCommandController.create(mockCreateCommand);
        });
    }

    @Test
    void createBrand_whenDescriptionIsMissing_shouldThrowBrandException() {
        // arrange
        BrandCreateCommand mockCreateCommand = new BrandCreateCommand();
        mockCreateCommand.setName("Valid Name");
        when(brandCreateHandler.execute(any(BrandCreateCommand.class)))
                .thenThrow(new BrandException("Description is required"));
        // act & Assert
        assertThrows(BrandException.class, () -> {
            brandCommandController.create(mockCreateCommand);
        });
    }
    @Test
    void createBrand_whenDescriptionExceedsMaxLength_shouldThrowBrandException() {
        // arrange
        BrandCreateCommand mockCreateCommand = new BrandCreateCommand();
        mockCreateCommand.setName("Valid Name");
        mockCreateCommand.setDescription( "A".repeat(121));
        when(brandCreateHandler.execute(any(BrandCreateCommand.class)))
                .thenThrow(new BrandException("Description exceeds maximum length of 120 characters"));
        // act & Assert
        assertThrows(BrandException.class, () -> {
            brandCommandController.create(mockCreateCommand);
        });
    }

}
