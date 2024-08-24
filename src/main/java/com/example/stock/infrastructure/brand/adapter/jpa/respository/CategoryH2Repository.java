package com.example.stock.infrastructure.brand.adapter.jpa.respository;

import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.port.repository.CategoryRepository;
import com.example.stock.infrastructure.brand.adapter.entity.CategoryEntity;
import com.example.stock.infrastructure.brand.adapter.jpa.CategorySpringJpaAdapterRepository;
import com.example.stock.infrastructure.brand.adapter.mapper.CategoryDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class CategoryH2Repository implements CategoryRepository {
    private final CategorySpringJpaAdapterRepository categorySpringJpaAdapterRepository;
    private final CategoryDboMapper categoryDboMapper;
    @Override
    public Category create(Category request) {
        CategoryEntity categoryToSave = categoryDboMapper.toDatabase(request);
        CategoryEntity categorySaved = categorySpringJpaAdapterRepository.save(categoryToSave);
        return categoryDboMapper.toDomain(categorySaved);
    }
    @Override
    public void deleteById(Long id) {
        categorySpringJpaAdapterRepository.deleteById(id);
    }
    @Override
    public Category update(Category request) {
        var categoryToUpdate = categoryDboMapper.toDatabase(request);
        var categoryUpdated = categorySpringJpaAdapterRepository.save(categoryToUpdate);
        return categoryDboMapper.toDomain(categoryUpdated);
    }
}
