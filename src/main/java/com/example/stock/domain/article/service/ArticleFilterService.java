package com.example.stock.domain.article.service;

import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.port.dao.ArticleDao;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class ArticleFilterService {
    private final ArticleDtoMapper articleDtoMapper;
    private final ArticleDao articleDao;
    public List<ArticleDto> execute (int page, int size, boolean ascending, String byName, String byBrand, String byCategory){
        return articleDao.getAll(page,size,ascending,byName,byBrand,byCategory)
                .stream()
                .map(articleDtoMapper::toDto)
                .toList();
    }
}
