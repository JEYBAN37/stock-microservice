package com.example.stock.category.categoryinfrasatructuretest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import com.example.stock.infrastructure.category.adapter.mapper.CategoryDboMapper;
import org.junit.jupiter.api.Test;

class CategoryDboMapperTest {

    private final CategoryDboMapper mapper = new CategoryDboMapper();

    @Test
    void testToDatabaseWithValidCategory() {
        // Datos de prueba
        Category category = new Category(1L, "CATEGORY NAME", "CATEGORY DESCRIPTION");

        // Ejecutar el método
        CategoryEntity entity = mapper.toDatabase(category);

        // Verificar resultados
        assertEquals(category.getId(), entity.getId());
        assertEquals(category.getName(), entity.getName());
        assertEquals(category.getDescription(), entity.getDescription());
    }

    @Test
    void testToDatabaseWithNullCategory() {
        // Ejecutar el método con entrada nula
        CategoryEntity entity = mapper.toDatabase(null);

        // Verificar que el resultado sea null
        assertNull(entity);
    }

    @Test
    void testToDomainWithValidCategoryEntity() {
        // Datos de prueba
        CategoryEntity entity = new CategoryEntity(1L, "CATEGORY NAME", "CATEGORY DESCRIPTION");

        // Ejecutar el método
        Category category = mapper.toDomain(entity);

        // Verificar resultados
        assertEquals(entity.getId(), category.getId());
        assertEquals(entity.getName(), category.getName());
        assertEquals(entity.getDescription(), category.getDescription());
    }

    @Test
    void testToDomainWithNullCategoryEntity() {
        // Ejecutar el método con entrada nula
        Category category = mapper.toDomain(null);

        // Verificar que el resultado sea null
        assertNull(category);
    }
}
