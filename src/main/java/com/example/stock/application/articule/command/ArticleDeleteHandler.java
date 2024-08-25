package com.example.stock.application.articule.command;


import com.example.stock.domain.article.service.ArticleDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleDeleteHandler {
    private final ArticleDeleteService articleDeleteService;
    public void execute(Long articleId) {
        articleDeleteService.execute(articleId);
    }
}
