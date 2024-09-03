package com.example.stock.infrastructure.category.rest.controller;
import com.example.stock.application.category.query.CategoryAllHandler;
import com.example.stock.application.category.query.CategoryByName;
import com.example.stock.domain.category.model.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/shared/category")
@AllArgsConstructor
@Tag(name ="Category Query Controller")
public class CategoryQueryController {

    private final CategoryAllHandler categoryAllHandler;
    private final CategoryByName categoryByName;


    @Operation(summary = "Get Category by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/name/{name}")
    public CategoryDto getByName(@PathVariable String name){
        return categoryByName.execute(name);
    }
    @Operation(summary = "Get All Brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public List<CategoryDto> getAll( @RequestParam() int page,
                                     @RequestParam() int size,
                                     @RequestParam() boolean ascending) {
        return categoryAllHandler.execute(page, size, ascending);
    }
}
