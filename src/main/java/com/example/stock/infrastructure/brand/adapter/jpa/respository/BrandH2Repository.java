package com.example.stock.infrastructure.brand.adapter.jpa.respository;

import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.port.repository.BrandRepository;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import com.example.stock.infrastructure.brand.adapter.jpa.BrandSpringJpaAdapterRepository;
import com.example.stock.infrastructure.brand.adapter.mapper.BrandDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class BrandH2Repository implements BrandRepository {
    private final BrandSpringJpaAdapterRepository brandSpringJpaAdapterRepository;
    private final BrandDboMapper brandDboMapper;
    @Override
    public Brand create(Brand request) {
        BrandEntity categoryToSave = brandDboMapper.toDatabase(request);
        BrandEntity categorySaved = brandSpringJpaAdapterRepository.save(categoryToSave);
        return brandDboMapper.toDomain(categorySaved);
    }
    @Override
    public void deleteById(Long id) {
        brandSpringJpaAdapterRepository.deleteById(id);
    }
    @Override
    public Brand update(Brand request) {
        var categoryToUpdate = brandDboMapper.toDatabase(request);
        var categoryUpdated = brandSpringJpaAdapterRepository.save(categoryToUpdate);
        return brandDboMapper.toDomain(categoryUpdated);
    }
}
