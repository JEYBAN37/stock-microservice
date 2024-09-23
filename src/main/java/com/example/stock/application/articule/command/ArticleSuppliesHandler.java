package com.example.stock.application.articule.command;


import com.example.stock.domain.article.model.dto.command.ArticleSupplyCommand;
import com.example.stock.domain.article.model.exception.ArticleException;
import com.example.stock.domain.article.service.ArticleSuppliesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class ArticleSuppliesHandler {
    private final ArticleSuppliesService articleSuppliesService;
    private final ObjectMapper objectMapper;
    public void execute(String articleSupply) {
        try {
            articleSuppliesService.execute(objectMapper.readValue(articleSupply, ArticleSupplyCommand.class));
        } catch (JsonProcessingException | ArticleException e) {
            System.err.println("Article error: " + e.getMessage());
            notifyUser("Error");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            notifyUser("Error");
        }
    }

    private void notifyUser(String message) {
        System.out.println("User notification: " + message);
    }

}
