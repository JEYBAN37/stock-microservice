package com.example.stock.infrastructure.brand.adapter.jpa.dao;

import com.example.stock.domain.brand.model.entity.Brand;
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

import static com.example.stock.domain.static_variables.StaticData.NAME;

@AllArgsConstructor
@Repository
public class BrandH2Dao implements BrandDao {

    private final BrandSpringJpaAdapterRepository brandSpringJpaAdapterRepository;
    private final BrandDboMapper brandDboMapper;

    @Override
    public Brand getByName(String name) {
        Optional<BrandEntity> optionalBrand = Optional.ofNullable(brandSpringJpaAdapterRepository.findByName(name));
        return optionalBrand.map(brandDboMapper::toDomain).orElse(null);
    }

    @Override
    public Brand getById(Long id) {
        Optional<BrandEntity> optionalBrand = (brandSpringJpaAdapterRepository.findById(id));
        return optionalBrand.map(brandDboMapper::toDomain).orElse(null);
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
        PageRequest pageRequest = PageRequest.of(page, size, ascending ? Sort.by(NAME).ascending() : Sort.by(NAME).descending());
        return brandSpringJpaAdapterRepository.findAll(pageRequest)
                .stream()
                .map(brandDboMapper::toDomain)
                .toList();
    }
}
