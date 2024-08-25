package com.example.stock.application.articule.query;


import com.example.stock.application.articule.mapper.ArticleDtoMapper;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.port.dao.ArticleDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleByName {
    private final ArticleDao articleDao;
    private final ArticleDtoMapper articleDtoMapper;
    public ArticleDto execute(String name) {
        return articleDtoMapper.toDto(articleDao.getByName(name));
    }
}
