package com.example.stock.infrastructure.brand.rest.controller;


import com.example.stock.application.brand.query.BrandByName;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.category.model.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandQueryController {
    private final BrandByName brandByName;
    @Operation(summary = "Get Brand by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "brand returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/name/{name}")
    public BrandDto getByName(@PathVariable String name){
        return brandByName.execute(name);
    }
    
}
