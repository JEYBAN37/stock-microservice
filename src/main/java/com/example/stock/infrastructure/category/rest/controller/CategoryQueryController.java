package com.example.stock.infrastructure.category.rest.controller;


import com.example.stock.application.category.query.*;
import com.example.stock.domain.category.model.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryQueryController {

    private final CategoryAllHandler categoryAllHandler;
    private final CategoryByName categoryByName;
    private  final CategoryAscendHandler categoryAscendHandler;
    private  final CategoryDescenHandler categoryDescenHandler;

    @Operation(summary = "Get Category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "category returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/name/{name}")
    public CategoryDto getByName(@PathVariable String name){
        return categoryByName.execute(name);
    }
    @Operation(summary = "Get All Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "categories returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })

    @GetMapping("/")
    public List<CategoryDto> getAll() {
        return categoryAllHandler.execute();
    }


    @Operation(summary = "Get All Categories by order Ascending")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "categories returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/filter/asc/")
    public List<CategoryDto> getAllAscend() {
        return categoryAscendHandler.execute();
    }
    @Operation(summary = "Get All Categories by order Deicing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "categories returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/filter/dsc/")
    public List<CategoryDto> getAllDescend() {
        return categoryDescenHandler.execute();
    }

}
