package com.example.stock.infrastructure.article.rest.controller;


import com.example.stock.application.articule.command.ArticleCreateHandler;
import com.example.stock.application.articule.command.ArticleDeleteHandler;
import com.example.stock.application.articule.command.ArticleUpdateHandler;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import com.example.stock.domain.article.model.dto.command.ArticleEditCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
@Tag(name ="Article Command Controller")
public class ArticleCommandController {
    private final ArticleCreateHandler articleCreateHandler;
    private final ArticleUpdateHandler articleUpdateHandler;
    private  final ArticleDeleteHandler articleDeleteHandler;


    @Operation(summary = "Add a new Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("/")
    public ArticleDto create(@RequestBody ArticleCreateCommand createCommand){
        return articleCreateHandler.execute(createCommand);
    }
    @Operation(summary = "Update an existing Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ArticleDto update(@RequestBody ArticleEditCommand editCommand, @PathVariable Long id){
        return articleUpdateHandler.execute(editCommand, id);
    }

    @Operation(summary = "Delete a Article by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        articleDeleteHandler.execute(id);
    }
}
