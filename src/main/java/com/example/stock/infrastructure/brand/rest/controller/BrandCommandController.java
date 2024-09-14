package com.example.stock.infrastructure.brand.rest.controller;


import com.example.stock.application.brand.command.BrandCreateHandler;
import com.example.stock.domain.brand.model.dto.BrandDto;
import com.example.stock.domain.brand.model.dto.command.BrandCreateCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/brands")
@AllArgsConstructor
@Tag(name ="Brand Command Controller")
public class BrandCommandController {
    private final BrandCreateHandler brandCreateHandler;


    @Operation(summary = "Add a new brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("/")
    public BrandDto create(@RequestBody BrandCreateCommand createCommand){
        return brandCreateHandler.execute(createCommand);
    }
}
