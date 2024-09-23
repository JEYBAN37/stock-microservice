package com.example.stock.infrastructure.article.rest.controller;


import com.example.stock.application.articule.command.ArticleCreateHandler;
import com.example.stock.application.articule.command.ArticleSuppliesHandler;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.article.model.dto.command.ArticleCreateCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@AllArgsConstructor
@Tag(name ="Article Command Controller")
public class ArticleCommandController {
    private final ArticleCreateHandler articleCreateHandler;
    private final ArticleSuppliesHandler articleSuppliesHandler;


    @Operation(summary = "Add a new Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("admin/articles/")
    public ArticleDto create(@RequestBody ArticleCreateCommand createCommand){
        return articleCreateHandler.execute(createCommand);
    }
}
