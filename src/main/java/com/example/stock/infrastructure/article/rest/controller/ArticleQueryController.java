package com.example.stock.infrastructure.article.rest.controller;


import com.example.stock.application.articule.query.ArticleByName;
import com.example.stock.application.articule.query.ArticleGetAll;
import com.example.stock.domain.article.model.dto.ArticleDto;
import com.example.stock.domain.category.model.dto.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/articles")
@AllArgsConstructor
@Tag(name ="Article Query Controller")
public class ArticleQueryController {
    private final ArticleByName articleByName;
    private final ArticleGetAll articleGetAll;
    @Operation(summary = "Get Article by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "article returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/name/{name}")
    public ArticleDto getByName(@PathVariable String name){
        return articleByName.execute(name);}

    @Operation(summary = "Get All Articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/")
    public List<ArticleDto> getAll(@RequestParam() int page,
                                   @RequestParam() int size,
                                   @RequestParam() boolean ascending,
                                   @RequestParam(required = false) String byName,
                                   @RequestParam(required = false) String byBrand,
                                   @RequestParam(required = false) String byCategory
                                   ) {
        return articleGetAll.execute(page, size, ascending,byName,byBrand,byCategory);
    }
}
