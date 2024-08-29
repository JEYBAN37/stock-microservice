package com.example.stock.infrastructure.brand.rest.controller;


import com.example.stock.application.brand.query.BrandGetAll;
import com.example.stock.application.brand.query.BrandById;
import com.example.stock.application.brand.query.BrandByName;
import com.example.stock.domain.brand.model.dto.BrandDto;
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
@RequestMapping("/brands")
@AllArgsConstructor
@Tag(name ="Brand Query Controller")
public class BrandQueryController {
    private final BrandByName brandByName;
    private final BrandById brandById;
    private final BrandGetAll brandGetAll;
    @Operation(summary = "Get Article by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "brand returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/name/{name}")
    public BrandDto getByName(@PathVariable String name){
        return brandByName.execute(name);
    }

    @Operation(summary = "Get Article by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "brand returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/id/{id}")
    public BrandDto getById( @PathVariable Long id){
        return brandById.execute(id);
    }

    @Operation(summary = "Get All Brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/")
    public List<BrandDto> getAll(@RequestParam() int page,
                                    @RequestParam() int size,
                                    @RequestParam() boolean ascending) {
        return brandGetAll.execute(page, size, ascending);
    }
}
