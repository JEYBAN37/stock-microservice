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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryQueryController {

    private final CategoryAllHandler categoryAllHandler;
    private final CategoryByName categoryByName;


    @Operation(summary = "Get Brand by name")
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
    public List<CategoryDto> getAll( @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "false") boolean ascending) {
        return categoryAllHandler.execute(page, size, ascending);
    }
}
