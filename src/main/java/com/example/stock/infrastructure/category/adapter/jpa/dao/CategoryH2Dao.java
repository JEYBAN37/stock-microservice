package com.example.stock.infrastructure.category.adapter.jpa.dao;

import com.example.stock.domain.category.model.constant.CategoryConstant;
import com.example.stock.domain.category.model.entity.Category;
import com.example.stock.domain.category.model.exception.CategoryException;
import com.example.stock.domain.category.port.dao.CategoryDao;
import com.example.stock.infrastructure.category.adapter.entity.CategoryEntity;
import com.example.stock.infrastructure.category.adapter.jpa.CategorySpringJpaAdapterRepository;
import com.example.stock.infrastructure.category.adapter.mapper.CategoryDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CategoryH2Dao implements CategoryDao {

    private final CategorySpringJpaAdapterRepository categorySpringJpaAdapterRepository;
    private final CategoryDboMapper categoryDboMapper;

    @Override
    public Category getByName(String name) {
        Optional<CategoryEntity> optionalCategory = Optional.ofNullable(categorySpringJpaAdapterRepository.findByName(name));
        if (optionalCategory.isEmpty()){
            throw new CategoryException(String.format(CategoryConstant.TASK_NOT_FOUND_MESSAGE_ERROR, name));
        }
        return categoryDboMapper.toDomain(optionalCategory.get());
    }

    @Override
    public Category getById(Long id) {
        Optional<CategoryEntity> optionalCategory = categorySpringJpaAdapterRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new CategoryException(String.format(CategoryConstant.TASK_NOT_FOUND_MESSAGE_ERROR, id));
        }
        return categoryDboMapper.toDomain(optionalCategory.get());
    }

    @Override
    public boolean nameExist(String name) {
        return categorySpringJpaAdapterRepository.existsByName(name);
    }

    @Override
    public boolean idExist(Long id) {
        return categorySpringJpaAdapterRepository.existsById(id);
    }

    @Override
    public List<Category> getall() {
        return categorySpringJpaAdapterRepository.findAll()
                .stream()
                .map(categoryDboMapper::toDomain)
                .toList();
    }

    @Override
    public List<Category> getFilterAsc() {
        return categorySpringJpaAdapterRepository.findAllByOrderByNameAsc()
                .stream()
                .map(categoryDboMapper::toDomain)
                .toList();
    }

    @Override
    public List<Category> getFilterDsc() {
        return categorySpringJpaAdapterRepository.findAllByOrderByNameDesc()
                .stream()
                .map(categoryDboMapper::toDomain)
                .toList();
    }
}