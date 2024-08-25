package com.example.stock.infrastructure.brand.rest.controller;


import com.example.stock.application.brand.command.BrandCreateHandler;
import com.example.stock.application.brand.command.BrandDeleteHandler;
import com.example.stock.application.brand.command.BrandUpdateHandler;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import com.example.stock.domain.brand.model.dto.command.BrandEditCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandCommandController {
    private final BrandCreateHandler brandCreateHandler;
    private final BrandUpdateHandler brandUpdateHandler;
    private  final BrandDeleteHandler brandDeleteHandler;


    @Operation(summary = "Add a new Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("/")
    public BrandDto create(@RequestBody BrandCreateCommand createCommand){
        return brandCreateHandler.execute(createCommand);
    }
    @Operation(summary = "Update an existing Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @PutMapping("/{id}")
    public BrandDto edit(@RequestBody BrandEditCommand brandEditCommand, @PathVariable Long id){
        return brandUpdateHandler.execute(brandEditCommand, id);
    }

    @Operation(summary = "Delete a brand by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        brandDeleteHandler.execute(id);
    }
}
