package com.example.stock.infrastructure.category.rest.controller;


import com.example.stock.application.category.command.CategoryCreateHandler;
import com.example.stock.domain.category.model.dto.CategoryDto;
import com.example.stock.domain.category.model.dto.command.CategoryCreateCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@AllArgsConstructor
@Tag(name ="Category Command Controller")
public class CategoryCommandController {
    private final CategoryCreateHandler categoryCreateHandler;


    @Operation(summary = "Add a new Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("/")
    public CategoryDto create(@RequestBody CategoryCreateCommand createCommand){
        return categoryCreateHandler.execute(createCommand);
    }
}
