package com.example.stock.infrastructure.brand.adapter.jpa.dao;

import com.example.stock.domain.brand.model.constant.BrandConstant;
import com.example.stock.domain.brand.model.entity.Brand;
import com.example.stock.domain.brand.model.exception.BrandException;
import com.example.stock.domain.brand.port.dao.BrandDao;
import com.example.stock.infrastructure.brand.adapter.entity.BrandEntity;
import com.example.stock.infrastructure.brand.adapter.jpa.BrandSpringJpaAdapterRepository;
import com.example.stock.infrastructure.brand.adapter.mapper.BrandDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class BrandH2Dao implements BrandDao {

    private final BrandSpringJpaAdapterRepository brandSpringJpaAdapterRepository;
    private final BrandDboMapper brandDboMapper;

    @Override
    public Brand getByName(String name) {
        Optional<BrandEntity> optionalBrand = Optional.ofNullable(brandSpringJpaAdapterRepository.findByName(name));
        if (optionalBrand.isEmpty()){
            throw new BrandException(String.format(BrandConstant.TASK_NOT_FOUND_MESSAGE_ERROR, name));
        }
        return brandDboMapper.toDomain(optionalBrand.get());
    }

    @Override
    public Brand getById(Long id) {
        Optional<BrandEntity> optionalbrand = brandSpringJpaAdapterRepository.findById(id);
        if (optionalbrand.isEmpty()){
            throw new BrandException(String.format(BrandConstant.TASK_NOT_FOUND_MESSAGE_ERROR, id));
        }
        return brandDboMapper.toDomain(optionalbrand.get());
    }

    @Override
    public boolean nameExist(String name) {
        return brandSpringJpaAdapterRepository.existsByName(name);
    }

    @Override
    public boolean idExist(Long id) {
        return brandSpringJpaAdapterRepository.existsById(id);
    }

    @Override
    public List<Brand> getAll( int page, int size, boolean ascending ) {
        PageRequest pageRequest = PageRequest.of(page, size, ascending ? Sort.by("name").ascending() : Sort.by("name").descending());
        return brandSpringJpaAdapterRepository.findAll(pageRequest)
                .stream()
                .map(brandDboMapper::toDomain)
                .toList();
    }
}
